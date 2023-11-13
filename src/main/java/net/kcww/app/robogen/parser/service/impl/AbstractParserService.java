package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.ParsingException;
import net.kcww.app.robogen.parser.exception.UncheckedParserConfigurationException;
import net.kcww.app.robogen.parser.service.ParserService;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Abstract base class for parser services. It provides common functionalities
 * for XML document parsing.
 */
@Slf4j
public abstract class AbstractParserService<T> implements ParserService<InputStream, T> {

    public static final String WILD_CARD = "*";

    // Constants for XML parsing features to prevent XXE attacks
    public static final String FEATURE_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
    public static final String FEATURE_EXTERNAL_GENERAL = "http://xml.org/sax/features/external-general-entities";
    public static final String FEATURE_EXTERNAL_PARAM = "http://xml.org/sax/features/external-parameter-entities";
    public static final String FEATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

    private final DocumentBuilderFactory factory;

    protected AbstractParserService() {
        this.factory = createAndConfigureFactory();
    }

    /**
     * Parses the given InputStream to produce an object of type T.
     * This method must be implemented by subclasses to define specific parsing logic.
     *
     * @param inputStream the input stream to parse.
     * @param <E>         the type of parsing exception that might be thrown.
     * @return an instance of type T representing the parsed data.
     * @throws E if a parsing error occurs.
     */
    @Override
    public abstract <E extends ParsingException> T parse(InputStream inputStream) throws E;

    /**
     * Builds a Document from the given InputStream.
     *
     * @param inputStream the input stream containing the XML data.
     * @return a Document object representing the parsed XML.
     * @throws SAXException if an error occurs during parsing.
     * @throws IOException  if an I/O error occurs.
     */
    protected Document buildDocument(InputStream inputStream) throws SAXException, IOException {
        DocumentBuilder builder = createDocumentBuilder();
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();
        return document;
    }

    /**
     * Creates a new instance of DocumentBuilder.
     *
     * @return a DocumentBuilder instance.
     * @throws UncheckedParserConfigurationException if a configuration error occurs.
     */
    private DocumentBuilder createDocumentBuilder() {
        try {
            return factory.newDocumentBuilder();
        } catch (javax.xml.parsers.ParserConfigurationException e) {
            log.error("Error configuring parser.", e);
            throw new UncheckedParserConfigurationException("Failed to create DocumentBuilder", e);
        }
    }

    /**
     * Creates and configures a DocumentBuilderFactory instance.
     *
     * @return a configured DocumentBuilderFactory instance.
     * @throws UncheckedParserConfigurationException if a configuration error occurs.
     */
    protected DocumentBuilderFactory createAndConfigureFactory() {
        var factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setNamespaceAware(true);
            factory.setFeature(FEATURE_DOCTYPE_DECL, true);
            factory.setFeature(FEATURE_EXTERNAL_GENERAL, false);
            factory.setFeature(FEATURE_EXTERNAL_PARAM, false);
            factory.setFeature(AbstractParserService.FEATURE_LOAD_EXTERNAL_DTD, false);
            return factory;
        } catch (ParserConfigurationException e) {
            log.error("Error setting XML parser features to prevent XXE attacks", e);
            throw new UncheckedParserConfigurationException("Failed to configure DocumentBuilderFactory", e);
        }
    }
}
