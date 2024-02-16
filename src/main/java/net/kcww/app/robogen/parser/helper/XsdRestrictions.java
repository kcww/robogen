package net.kcww.app.robogen.parser.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.model.XsdElement;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static net.kcww.app.robogen.parser.helper.Elements.setAttributeIfPresent;
import static net.kcww.app.robogen.parser.helper.Elements.toStream;
import static net.kcww.app.robogen.parser.helper.XsdAttributeEnum.*;
import static net.kcww.app.robogen.parser.helper.XsdElementEnum.RESTRICTION;
import static net.kcww.app.robogen.parser.helper.XsdElementEnum.SIMPLE_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class XsdRestrictions {

    private static final Map<String, BiConsumer<Element, XsdElement.Restriction>> restrictionChildHandlers = Map.of(
            PATTERN.attrName(), XsdRestrictions::handlePattern,
            MIN_LENGTH.attrName(), XsdRestrictions::handleMinLength,
            MAX_LENGTH.attrName(), XsdRestrictions::handleMaxLength,
            MIN_INCLUSIVE.attrName(), XsdRestrictions::handleMinInclusive,
            MAX_INCLUSIVE.attrName(), XsdRestrictions::handleMaxInclusive,
            ENUMERATION.attrName(), XsdRestrictions::handleEnumeration
    );

    public static Optional<XsdElement.Restriction> get(Element xsdElement) {
        return findSimpleTypeElement(xsdElement)
                .flatMap(XsdRestrictions::findRestrictionElement)
                .map(XsdRestrictions::buildRestriction);
    }

    // To get the first SimpleType element
    public static Optional<Element> findSimpleTypeElement(Element element) {
        return toStream(element.getChildNodes())
                .filter(child -> SIMPLE_TYPE.tagName().equals(child.getLocalName()))
                .findFirst();
    }

    private static Optional<Element> findRestrictionElement(Element simpleType) {
        return toStream(simpleType.getChildNodes())
                .filter(child -> RESTRICTION.tagName().equals(child.getLocalName()))
                .findFirst();
    }

    private static XsdElement.Restriction buildRestriction(Element restriction) {
        var model = new XsdElement.Restriction();
        setAttributeIfPresent(restriction, BASE.attrName(), Elements::stripPrefix, model::base);
        setRestrictionChildrenIfPresent(restriction, model);
        return model;
    }

    private static void setRestrictionChildrenIfPresent(Element restriction, XsdElement.Restriction model) {
        toStream(restriction.getChildNodes()).forEach(child -> handleRestrictionChild(child, model));
    }

    private static void handleRestrictionChild(Element element, XsdElement.Restriction restriction) {
        restrictionChildHandlers
                .getOrDefault(element.getLocalName(), XsdRestrictions::logUnexpectedRestrictionType)
                .accept(element, restriction);
    }

    private static void logUnexpectedRestrictionType(Element e, XsdElement.Restriction r) {
        log.warn("Unexpected restriction type: {}", e.getLocalName());
    }

    private static void handlePattern(Element element, XsdElement.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Function.identity(), restriction::pattern);
    }

    private static void handleMinLength(Element element, XsdElement.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::minLength);
    }

    private static void handleMaxLength(Element element, XsdElement.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::maxLength);
    }

    private static void handleMinInclusive(Element element, XsdElement.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::minInclusive);
    }

    private static void handleMaxInclusive(Element element, XsdElement.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::maxInclusive);
    }

    private static void handleEnumeration(Element element, XsdElement.Restriction restriction) {
        Optional.of(element.getAttribute(VALUE.attrName())).ifPresent(value -> {
            var enums = Optional.ofNullable(restriction.enumerations()).orElseGet(ArrayList::new);
            enums.add(value);
            restriction.enumerations(enums);
        });
    }
}
