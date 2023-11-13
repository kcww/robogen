package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.XmlParsingException;
import net.kcww.app.robogen.parser.helper.XmlElements;
import net.kcww.app.robogen.parser.model.XmlElementModel;
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

/**
 * Service implementation for parsing XML files.
 */
@Service
@Slf4j
public class XmlParserServiceImpl extends AbstractParserService<List<XmlElementModel>> {

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
            throw new XmlParsingException("Failed to parse XML input stream", e);
        }
    }

    /**
     * Extracts XML elements from a given Document and maps them to XmlElementModel.
     *
     * @param document the Document object representing the XML content.
     * @return a list of XmlElementModel instances.
     */
    private List<XmlElementModel> extractElements(Document document) {
        return streamElements(document)
                .filter(XmlElements::hasValidAttribute)
                .map(XmlElements::create)
                // .peek(model -> log.info("Extracted XML element: {}", model))
                .collect(Collectors.toList());
    }

    /**
     * Creates a stream of Element objects from the given Document.
     *
     * @param document the Document object representing the XML content.
     * @return a stream of Element objects.
     */
    private Stream<Element> streamElements(Document document) {
        return toStream(document.getElementsByTagName(WILD_CARD));
    }
}
