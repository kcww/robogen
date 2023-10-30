package net.kcww.app.robogen.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.service.AbstractParserService;
import net.kcww.app.robogen.model.XsdModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Service
@Slf4j
public class XsdParserServiceImpl extends AbstractParserService<XsdModel> {

  @Override
  public List<XsdModel> parse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
    var doc = buildDocument(inputStream);
    List<XsdModel> result = extractElements(doc.getDocumentElement());
    log.info("Parsed XSD file: {}", result);
    return result;
  }

  @Override
  protected void configureFactory(DocumentBuilderFactory factory) throws ParserConfigurationException {
    factory.setNamespaceAware(true);
    factory.setFeature(FEATURE_DOCTYPE_DECL, true);
    factory.setFeature(FEATURE_EXTERNAL_GENERAL, false);
    factory.setFeature(FEATURE_EXTERNAL_PARAM, false);
    factory.setFeature(FEATURE_LOAD_EXTERNAL_DTD, false);
  }

  private List<XsdModel> extractElements(Node node) {
    List<XsdModel> elements = new ArrayList<>();
    toStream(node.getChildNodes()).forEach(child -> {
      if (isElementType(child)) {
        elements.add(createElementFromNode(child));
      }
      elements.addAll(extractElements(child));
    });
    return elements;
  }

  private boolean isElementType(Node node) {
    return XsdAttr.ELEMENT.equals(node.getLocalName());
  }

  private XsdModel createElementFromNode(Node node) {
    Element element = (Element) node;
    XsdModel xsdElement = new XsdModel();

    xsdElement.setName(element.getAttribute(XsdAttr.NAME));
    xsdElement.setType(stripPrefix(element.getAttribute(XsdAttr.TYPE)));

    determineMinOccurs(element, XsdAttr.NILLABLE).ifPresent(xsdElement::setMinOccurs);
    getAttributeValueAsInt(element, XsdAttr.MIN_OCCURS).ifPresent(xsdElement::setMinOccurs);

    xsdElement.setRestriction(findRestriction(element));

    return xsdElement;
  }

  private Optional<Integer> determineMinOccurs(Element element, String attributeName) {
    return Optional.ofNullable(element.getAttribute(attributeName))
      .filter(value -> !value.isEmpty())
      .map(value -> value.equals("true") ? 0 : 1);
  }

  private Optional<Integer> getAttributeValueAsInt(Element element, String attributeName) {
    return Optional.of(element.getAttribute(attributeName))
      .filter(value -> !value.isEmpty())
      .map(Integer::parseInt);
  }

  private XsdModel.Restriction findRestriction(Element element) {
    return getChildElement(element, this::isSimpleType)
      .map(this::createRestrictionFromSimpleType)
      .orElse(null);
  }

  private Optional<Element> getChildElement(Element parent, Predicate<Node> filter) {
    return toStream(parent.getChildNodes())
      .filter(filter)
      .map(Element.class::cast)
      .findFirst();
  }

  private boolean isSimpleType(Node node) {
    return XsdAttr.SIMPLE_TYPE.equals(node.getLocalName());
  }

  private XsdModel.Restriction createRestrictionFromSimpleType(Element simpleTypeElement) {
    return getChildElement(simpleTypeElement, this::isRestrictionType)
      .map(this::populateRestrictionFromElement)
      .orElse(null);
  }

  private boolean isRestrictionType(Node node) {
    return XsdAttr.RESTRICTION.equals(node.getLocalName());
  }

  private XsdModel.Restriction populateRestrictionFromElement(Element restrictionElement) {
    XsdModel.Restriction restriction = new XsdModel.Restriction();
    restriction.setBase(stripPrefix(restrictionElement.getAttribute(XsdAttr.BASE)));

    toStream(restrictionElement.getChildNodes())
      .filter(child -> child instanceof Element)
      .forEach(child -> handleChildElementAttributes((Element) child, restriction));

    return restriction;
  }

  private void handleChildElementAttributes(Element childElement, XsdModel.Restriction restriction) {
    String nodeName = childElement.getLocalName();
    if (restrictionHandlers.containsKey(nodeName)) {
      restrictionHandlers.get(nodeName).accept(childElement, restriction);
    } else {
      log.warn("Unexpected restriction type: {}", nodeName);
    }
  }

  private final Map<String, BiConsumer<Element, XsdModel.Restriction>> restrictionHandlers = Map.of(
    XsdAttr.ENUMERATION, this::handleEnumeration,
    XsdAttr.MIN_LENGTH, this::handleMinLength,
    XsdAttr.MAX_LENGTH, this::handleMaxLength,
    XsdAttr.MIN_INCLUSIVE, this::handleMinInclusive,
    XsdAttr.MAX_INCLUSIVE, this::handleMaxInclusive,
    XsdAttr.PATTERN, this::handlePattern
  );

  private void handleEnumeration(Element childElement, XsdModel.Restriction restriction) {
    if (restriction.getEnumerations() == null) {
      restriction.setEnumerations(new ArrayList<>());
    }
    restriction.getEnumerations().add(childElement.getAttribute(XsdAttr.VALUE));
  }

  private void handleMinLength(Element childElement, XsdModel.Restriction restriction) {
    getAttributeValueAsInt(childElement, XsdAttr.VALUE).ifPresent(restriction::setMinLength);
  }

  private void handleMaxLength(Element childElement, XsdModel.Restriction restriction) {
    getAttributeValueAsInt(childElement, XsdAttr.VALUE).ifPresent(restriction::setMaxLength);
  }

  private void handleMinInclusive(Element childElement, XsdModel.Restriction restriction) {
    getAttributeValueAsInt(childElement, XsdAttr.VALUE).ifPresent(restriction::setMinInclusive);
  }

  private void handleMaxInclusive(Element childElement, XsdModel.Restriction restriction) {
    getAttributeValueAsInt(childElement, XsdAttr.VALUE).ifPresent(restriction::setMaxInclusive);
  }

  private void handlePattern(Element childElement, XsdModel.Restriction restriction) {
    restriction.setPattern(childElement.getAttribute(XsdAttr.VALUE));
  }

  interface XsdAttr {
    String ELEMENT = "element";
    String SIMPLE_TYPE = "simpleType";

    String NAME = "name";
    String TYPE = "type";

    String MIN_OCCURS = "minOccurs";
    String NILLABLE = "nillable";

    String RESTRICTION = "restriction";

    String BASE = "base";
    String PATTERN = "pattern";
    String MIN_LENGTH = "minLength";
    String MAX_LENGTH = "maxLength";
    String MIN_INCLUSIVE = "minInclusive";
    String MAX_INCLUSIVE = "maxInclusive";
    String ENUMERATION = "enumeration";

    String VALUE = "value";
  }

}