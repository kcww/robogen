package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.XsdParsingException;
import net.kcww.app.robogen.parser.helper.Elements;
import net.kcww.app.robogen.parser.helper.XsdRestrictions;
import net.kcww.app.robogen.parser.model.XsdElement;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

import static net.kcww.app.robogen.parser.helper.Elements.setAttributeIfPresent;
import static net.kcww.app.robogen.parser.helper.Elements.toStream;
import static net.kcww.app.robogen.parser.helper.XsdAttributeEnum.*;
import static net.kcww.app.robogen.parser.helper.XsdElementEnum.ELEMENT;
import static net.kcww.app.robogen.parser.helper.XsdElementEnum.SIMPLE_TYPE;

/**
 * Service implementation for parsing XSD (XML Schema Definition) files.
 */
@Service
@Slf4j
public class XsdParserServiceImpl extends AbstractParserService<List<XsdElement>> {

    private static final String PARSING_ERROR_MSG = "Error parsing XSD input stream: ";

    @Override
    public List<XsdElement> parse(InputStream inputStream) throws XsdParsingException {
        try {
            Document document = buildDocument(inputStream);
            return extractElements(document);
        } catch (IOException | SAXException e) {
            String detailedError = PARSING_ERROR_MSG + e.getMessage();
            log.error(detailedError, e);
            throw new XsdParsingException(detailedError, e);
        }
    }

    private List<XsdElement> extractElements(Document document) {
        return toStream(document.getElementsByTagNameNS(WILD_CARD, ELEMENT.tagName()))
                .filter(this::hasSimpleTypeAsDirectChild)
                .map(this::createXsdElement)
                .toList();
    }

    // To check if the element has a SimpleType element as a direct child
    private boolean hasSimpleTypeAsDirectChild(Element element) {
        return toStream(element.getChildNodes())
                .anyMatch(child -> SIMPLE_TYPE.tagName().equals(child.getLocalName()));
    }

    private XsdElement createXsdElement(Element element) {
        var builder = XsdElement.builder();
        populateAttributes(element, builder);
        populateRestriction(element, builder);
        return builder.build();
    }

    private void populateAttributes(Element element, XsdElement.XsdElementBuilder builder) {
        setAttributeIfPresent(element, NAME.attrName(), Function.identity(), builder::name);
        setAttributeIfPresent(element, TYPE.attrName(), Elements::stripPrefix, builder::type);
        setAttributeIfPresent(element, NILLABLE.attrName(), Boolean::valueOf, builder::nillable);
        setAttributeIfPresent(element, MIN_OCCURS.attrName(), Integer::valueOf, builder::minOccurs);
        setAttributeIfPresent(element, MAX_OCCURS.attrName(), Integer::valueOf, builder::maxOccurs);
    }

    private void populateRestriction(Element element, XsdElement.XsdElementBuilder builder) {
        XsdRestrictions.get(element).ifPresent(builder::restriction);
    }
}
