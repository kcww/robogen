package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.XmlParsingException;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static net.kcww.app.robogen.parser.helper.Elements.*;
import static net.kcww.app.robogen.parser.model.XmlAttributeEnum.*;

/**
 * Service implementation for parsing XML files.
 */
@Service
@Slf4j
public class XmlParserServiceImpl extends AbstractParserService<List<XmlElementModel>> {

    private static final List<String> identities = Arrays.asList(ID.attrName(), DEBUG_ID.attrName(), NAME.attrName());

    /**
     * Parses an InputStream of an XML file and extracts XML elements.
     *
     * @param inputStream the input stream of the XML file.
     * @return a list of XmlElementModels representing the elements in the XML file.
     * @throws XmlParsingException if parsing fails due to I/O or SAX errors.
     */
    @Override
    public List<XmlElementModel> parse(InputStream inputStream) throws XmlParsingException {
        try {
            Document document = buildDocument(inputStream);
            return extractElements(document);
        } catch (IOException | SAXException e) {
            log.error("Error parsing XML input stream", e);
            throw new XmlParsingException("Failed to parse XML input stream", e);
        }
    }

    private List<XmlElementModel> extractElements(Document document) {
        return streamElements(document)
                .filter(this::hasValidIdentifiableAttribute)
                .map(this::createXmlElementModel)
                .toList();
    }

    private Stream<Element> streamElements(Document document) {
        return toStream(document.getElementsByTagName(WILD_CARD));
    }

    private boolean hasValidIdentifiableAttribute(Element element) {
        return identities.stream().anyMatch(element::hasAttribute);
    }

    private XmlElementModel createXmlElementModel(Element element) {
        var model = new XmlElementModel();
        model.tagName(stripPrefix(element.getNodeName()));
        populateAttributes(element, model);
        return model;
    }

    private void populateAttributes(Element element, XmlElementModel model) {
        model.id(determineId(element));
        setCommonAttributes(element, model);
    }

    private String determineId(Element element) {
        if (element.hasAttribute(DEBUG_ID.attrName())) return element.getAttribute(DEBUG_ID.attrName());
        if (element.hasAttribute(ID.attrName())) return element.getAttribute(ID.attrName());
        return element.getAttribute(NAME.attrName());
    }

    private void setCommonAttributes(Element element, XmlElementModel model) {
        setAttributeIfPresent(element, NAME.attrName(), Function.identity(), model::name);
        setAttributeIfPresent(element, LABEL.attrName(), Function.identity(), model::label);
        setAttributeIfPresent(element, VALUE.attrName(), Function.identity(), model::value);
        setAttributeIfPresent(element, MIN.attrName(), Function.identity(), model::min);
        setAttributeIfPresent(element, MAX.attrName(), Function.identity(), model::max);
        setAttributeIfPresent(element, REQUIRED.attrName(), Boolean::valueOf, model::required);
    }
}
