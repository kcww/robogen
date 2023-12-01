package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.XsdParsingException;
import net.kcww.app.robogen.parser.helper.Elements;
import net.kcww.app.robogen.parser.helper.XsdRestrictions;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.kcww.app.robogen.parser.helper.Elements.setAttributeIfPresent;
import static net.kcww.app.robogen.parser.helper.Elements.toStream;
import static net.kcww.app.robogen.parser.model.XsdAttributeEnum.*;
import static net.kcww.app.robogen.parser.model.XsdElementEnum.ELEMENT;
import static net.kcww.app.robogen.parser.model.XsdElementEnum.SIMPLE_TYPE;

/**
 * Service implementation for parsing XSD (XML Schema Definition) files.
 */
@Service
@Slf4j
public class XsdParserServiceImpl extends AbstractParserService<List<XsdElementModel>> {

    /**
     * Parses an InputStream of an XSD file and extracts XSD elements.
     *
     * @param inputStream the input stream of the XSD file.
     * @return a list of XsdElementModels representing the elements in the XSD file.
     * @throws XsdParsingException if parsing fails due to I/O or SAX errors.
     */
    @Override
    public List<XsdElementModel> parse(InputStream inputStream) throws XsdParsingException {
        try {
            Document document = buildDocument(inputStream);
            return extractElements(document);
        } catch (IOException | SAXException e) {
            log.error("Error parsing XSD input stream", e);
            throw new XsdParsingException("Failed to parse XSD input stream", e);
        }
    }

    private List<XsdElementModel> extractElements(Document document) {
        return streamElements(document)
                .filter(this::hasSimpleTypeAsDirectChild)
                .map(this::createXsdElementModel)
                .collect(Collectors.toList());
    }

    private Stream<Element> streamElements(Document document) {
        return toStream(document.getElementsByTagNameNS(WILD_CARD, ELEMENT.tagName()));
    }

    // To check if the element has a SimpleType element as a direct child
    public boolean hasSimpleTypeAsDirectChild(Element element) {
        return toStream(element.getChildNodes()).anyMatch(child -> SIMPLE_TYPE.tagName().equals(child.getLocalName()));
    }

    public XsdElementModel createXsdElementModel(Element element) {
        XsdElementModel model = new XsdElementModel();
        populateAttributes(element, model);
        populateRestriction(element, model);
        return model;
    }

    private void populateAttributes(Element element, XsdElementModel model) {
        setAttributeIfPresent(element, NAME.attrName(), Function.identity(), model::name);
        setAttributeIfPresent(element, TYPE.attrName(), Elements::stripPrefix, model::type);
        setAttributeIfPresent(element, NILLABLE.attrName(), Boolean::valueOf, model::nillable);
        setAttributeIfPresent(element, MIN_OCCURS.attrName(), Integer::valueOf, model::minOccurs);
        setAttributeIfPresent(element, MAX_OCCURS.attrName(), Integer::valueOf, model::maxOccurs);
    }

    private void populateRestriction(Element element, XsdElementModel model) {
        XsdRestrictions.get(element).ifPresent(model::restriction);
    }
}
