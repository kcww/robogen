package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.XmlParsingException;
import net.kcww.app.robogen.parser.model.XmlElement;
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
import static net.kcww.app.robogen.parser.helper.XmlAttributeEnum.*;

/**
 * Service implementation for parsing XML files.
 */
@Service
@Slf4j
public class XmlParserServiceImpl extends AbstractParserService<List<XmlElement>> {

    private static final List<String> IDENTITY_ATTRIBUTES = Arrays.asList(ID.attrName(), NAME.attrName());
    private static final String PARSING_ERROR_MSG = "Error parsing XML input stream: ";

    @Override
    public List<XmlElement> parse(InputStream inputStream) throws XmlParsingException {
        try {
            Document document = buildDocument(inputStream);
            return extractElements(document);
        } catch (IOException | SAXException e) {
            String detailedError = PARSING_ERROR_MSG + e.getMessage();
            log.error(detailedError, e);
            throw new XmlParsingException(detailedError, e);
        }
    }

    private List<XmlElement> extractElements(Document document) {
        return streamElements(document)
                .filter(this::hasValidIdentifiableAttribute)
                .map(this::createXmlElement)
                .toList();
    }

    private Stream<Element> streamElements(Document document) {
        return toStream(document.getElementsByTagName(WILD_CARD));
    }

    private boolean hasValidIdentifiableAttribute(Element element) {
        return IDENTITY_ATTRIBUTES.stream().anyMatch(element::hasAttribute);
    }

    private XmlElement createXmlElement(Element element) {
        var builder = XmlElement.builder().tagName(stripPrefix(element.getNodeName()));
        builder.id(determineId(element));
        populateAttributes(element, builder);
        return builder.build();
    }

    private String determineId(Element element) {
        return element.hasAttribute(ID.attrName()) ? element.getAttribute(ID.attrName()) : element.getAttribute(NAME.attrName());
    }

    private void populateAttributes(Element element, XmlElement.XmlElementBuilder builder) {
        setAttributeIfPresent(element, NAME.attrName(), Function.identity(), builder::name);
        setAttributeIfPresent(element, LABEL.attrName(), Function.identity(), builder::label);
        setAttributeIfPresent(element, VALUE.attrName(), Function.identity(), builder::value);
        setAttributeIfPresent(element, MIN.attrName(), Function.identity(), builder::min);
        setAttributeIfPresent(element, MAX.attrName(), Function.identity(), builder::max);
        setAttributeIfPresent(element, REQUIRED.attrName(), Boolean::valueOf, builder::required);
    }
}
