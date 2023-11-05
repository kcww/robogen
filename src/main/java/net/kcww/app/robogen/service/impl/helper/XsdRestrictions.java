package net.kcww.app.robogen.service.impl.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.model.XsdElementModel;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static net.kcww.app.robogen.model.XsdAttribute.*;
import static net.kcww.app.robogen.model.XsdElement.RESTRICTION;
import static net.kcww.app.robogen.service.impl.helper.Elements.setAttributeIfPresent;
import static net.kcww.app.robogen.service.impl.helper.Elements.toStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class XsdRestrictions {

  private static final Map<String, BiConsumer<Element, XsdElementModel.Restriction>> restrictionChildHandlers = Map.of(
    PATTERN.getName(), XsdRestrictions::handlePattern,
    MIN_LENGTH.getName(), XsdRestrictions::handleMinLength,
    MAX_LENGTH.getName(), XsdRestrictions::handleMaxLength,
    MIN_INCLUSIVE.getName(), XsdRestrictions::handleMinInclusive,
    MAX_INCLUSIVE.getName(), XsdRestrictions::handleMaxInclusive,
    ENUMERATION.getName(), XsdRestrictions::handleEnumeration
  );

  public static Optional<XsdElementModel.Restriction> get(Element xsdElement) {
    return XsdSimpleTypes.get(xsdElement)
      .flatMap(XsdRestrictions::extract)
      .map(XsdRestrictions::create);
  }

  private static Optional<Element> extract(Element simpleType) {
    return toStream(simpleType.getChildNodes())
      .filter(child -> RESTRICTION.getName().equals(child.getLocalName()))
      .findFirst();
  }

  private static XsdElementModel.Restriction create(Element restriction) {
    var model = new XsdElementModel.Restriction();
    setAttributeIfPresent(restriction, BASE.getName(), Elements::stripPrefix, model::setBase);
    setRestrictionChildrenIfPresent(restriction, model);
    return model;
  }

  private static void setRestrictionChildrenIfPresent(Element restriction, XsdElementModel.Restriction model) {
    toStream(restriction.getChildNodes()).forEach(child -> applyRestrictionChildHandlers(child, model));
  }

  private static void applyRestrictionChildHandlers(Element element, XsdElementModel.Restriction restriction) {
    restrictionChildHandlers
      .getOrDefault(element.getLocalName(), XsdRestrictions::logUnexpectedRestrictionType)
      .accept(element, restriction);
  }

  private static void logUnexpectedRestrictionType(Element e, XsdElementModel.Restriction r) {
    log.warn("Unexpected restriction type: {}", e.getLocalName());
  }

  private static void handlePattern(Element element, XsdElementModel.Restriction restriction) {
    setAttributeIfPresent(element, VALUE.getName(), Function.identity(), restriction::setPattern);
  }

  private static void handleMinLength(Element element, XsdElementModel.Restriction restriction) {
    setAttributeIfPresent(element, VALUE.getName(), Integer::valueOf, restriction::setMinLength);
  }

  private static void handleMaxLength(Element element, XsdElementModel.Restriction restriction) {
    setAttributeIfPresent(element, VALUE.getName(), Integer::valueOf, restriction::setMaxLength);
  }

  private static void handleMinInclusive(Element element, XsdElementModel.Restriction restriction) {
    setAttributeIfPresent(element, VALUE.getName(), Integer::valueOf, restriction::setMinInclusive);
  }

  private static void handleMaxInclusive(Element element, XsdElementModel.Restriction restriction) {
    setAttributeIfPresent(element, VALUE.getName(), Integer::valueOf, restriction::setMaxInclusive);
  }

  private static void handleEnumeration(Element element, XsdElementModel.Restriction restriction) {
    Optional.of(element.getAttribute(VALUE.getName())).ifPresent(value -> {
      var enums = Optional.ofNullable(restriction.getEnumerations()).orElseGet(ArrayList::new);
      enums.add(value);
      restriction.setEnumerations(enums);
    });
  }
}
