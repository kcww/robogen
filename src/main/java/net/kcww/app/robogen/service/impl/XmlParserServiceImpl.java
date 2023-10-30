package net.kcww.app.robogen.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.service.AbstractParserService;
import net.kcww.app.robogen.model.XmlModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class XmlParserServiceImpl extends AbstractParserService<XmlModel> {

  @Override
  public List<XmlModel> parse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
    Document doc = buildDocument(inputStream);
    List<XmlModel> elements = extractElements(doc);
    log.info("Parsed XML file: {}", elements);
    return elements;
  }

  @Override
  protected void configureFactory(DocumentBuilderFactory factory) throws ParserConfigurationException {
    factory.setFeature(FEATURE_DOCTYPE_DECL, true);
    factory.setFeature(FEATURE_EXTERNAL_GENERAL, false);
    factory.setFeature(FEATURE_EXTERNAL_PARAM, false);
  }

  private List<XmlModel> extractElements(Document doc) {
    return toStream(doc.getElementsByTagName(XmlAttr.WILD_CARD))
      .filter(this::isRelevantElement)
      .map(Element.class::cast)
      .map(this::mapElementToXmlModel)
      .collect(Collectors.toList());
  }

  private boolean isRelevantElement(Node node) {
    return node.getNodeType() == Node.ELEMENT_NODE
      && (hasAttribute((Element) node, XmlAttr.ID)
      || hasAttribute((Element) node, XmlAttr.DEBUG_ID)
      || hasAttribute((Element) node, XmlAttr.NAME));
  }

  private boolean hasAttribute(Element element, String attributeName) {
    return element.hasAttribute(attributeName) && !element.getAttribute(attributeName).isBlank();
  }

  private XmlModel mapElementToXmlModel(Element element) {
    XmlModel uiModel = new XmlModel();

    uiModel.setTag(stripPrefix(element.getNodeName()));
    uiModel.setId(getIdAttribute(element, XmlAttr.ID, XmlAttr.DEBUG_ID));
    uiModel.setName(element.getAttribute(XmlAttr.NAME));
    uiModel.setLabel(element.getAttribute(XmlAttr.LABEL));
    uiModel.setValue(element.getAttribute(XmlAttr.VALUE));
    uiModel.setMin(getNumericAttribute(element, XmlAttr.MIN));
    uiModel.setMax(getNumericAttribute(element, XmlAttr.MAX));
    uiModel.setRequired(element.hasAttribute(XmlAttr.REQUIRED));

    return uiModel;
  }

  private String getIdAttribute(Element element, String... attributeNames) {
    for (String attributeName : attributeNames) {
      String attrValue = element.getAttribute(attributeName);
      if (!attrValue.isBlank()) {
        return attrValue;
      }
    }
    return "";
  }


  private int getNumericAttribute(Element element, String attributeName) {
    return Optional.ofNullable(element.getAttribute(attributeName))
      .filter(attrValue -> !attrValue.isBlank())
      .map(Integer::parseInt)
      .orElse(0);
  }

  interface XmlAttr {
    String WILD_CARD = "*";
    String ID = "id";
    String DEBUG_ID = "debugId";
    String NAME = "name";
    String LABEL = "label";
    String VALUE = "value";
    String MIN = "min";
    String MAX = "max";
    String REQUIRED = "required";
  }

}