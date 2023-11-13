package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.XsdParsingException;
import net.kcww.app.robogen.parser.helper.XsdElements;
import net.kcww.app.robogen.parser.helper.XsdSimpleTypes;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.kcww.app.robogen.parser.helper.Elements.toStream;
import static net.kcww.app.robogen.parser.model.XsdElementEnum.ELEMENT;

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
            throw new XsdParsingException("Failed to parse XSD input stream", e);
        }
    }

    /**
     * Extracts XSD elements from a given Document and maps them to XsdElementModel.
     *
     * @param document the Document object representing the XSD content.
     * @return a list of XsdElementModel instances.
     */
    private List<XsdElementModel> extractElements(Document document) {
        return streamElements(document)
                .filter(XsdSimpleTypes::hasSimpleTypeAsDirectChild)
                .map(XsdElements::create)
                .collect(Collectors.toList());
    }

    /**
     * Creates a stream of Element objects from the given Document.
     *
     * @param document the Document object representing the XSD content.
     * @return a stream of Element objects.
     */
    private Stream<Element> streamElements(Document document) {
        return toStream(document.getElementsByTagNameNS(WILD_CARD, ELEMENT.getName()));
    }
}
