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
public abstract class AbstractParserService<V> implements ParserService<InputStream, V> {

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

    @Override
    public abstract <E extends ParsingException> V parse(InputStream inputStream) throws E;

    protected Document buildDocument(InputStream inputStream) throws SAXException, IOException {
        DocumentBuilder builder = createDocumentBuilder();
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();
        return document;
    }

    private DocumentBuilder createDocumentBuilder() {
        try {
            return factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error("Error configuring parser.", e);
            throw new UncheckedParserConfigurationException("Failed to create DocumentBuilder", e);
        }
    }

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
