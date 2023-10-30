package net.kcww.app.robogen.service;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public abstract class AbstractParserService<T> implements ParserService<T> {

  public static final String FEATURE_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
  public static final String FEATURE_EXTERNAL_GENERAL = "http://xml.org/sax/features/external-general-entities";
  public static final String FEATURE_EXTERNAL_PARAM = "http://xml.org/sax/features/external-parameter-entities";
  public static final String FEATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

  private DocumentBuilderFactory factory;

  protected Document buildDocument(InputStream inputStream)
    throws ParserConfigurationException, SAXException, IOException {

    DocumentBuilder builder = getFactory().newDocumentBuilder();
    Document doc = builder.parse(inputStream);
    doc.getDocumentElement().normalize();
    return doc;
  }

  private synchronized DocumentBuilderFactory getFactory() {
    if (factory == null) {
      factory = createAndConfigureFactory();
    }
    return factory;
  }

  protected DocumentBuilderFactory createAndConfigureFactory() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      configureFactory(factory);
    } catch (ParserConfigurationException e) {
      log.error("Error configuring parser to defend against XXE.", e);
      throw new RuntimeException(e);
    }
    return factory;
  }

  protected abstract void configureFactory(DocumentBuilderFactory factory) throws ParserConfigurationException;

  protected Stream<Node> toStream(NodeList nodeList) {
    return IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item);
  }

}