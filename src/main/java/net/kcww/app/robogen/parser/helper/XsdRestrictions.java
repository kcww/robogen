package net.kcww.app.robogen.parser.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static net.kcww.app.robogen.parser.helper.Elements.setAttributeIfPresent;
import static net.kcww.app.robogen.parser.helper.Elements.toStream;
import static net.kcww.app.robogen.parser.model.XsdAttributeEnum.*;
import static net.kcww.app.robogen.parser.model.XsdElementEnum.RESTRICTION;
import static net.kcww.app.robogen.parser.model.XsdElementEnum.SIMPLE_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class XsdRestrictions {

    private static final Map<String, BiConsumer<Element, XsdElementModel.Restriction>> restrictionChildHandlers = Map.of(
            PATTERN.attrName(), XsdRestrictions::handlePattern,
            MIN_LENGTH.attrName(), XsdRestrictions::handleMinLength,
            MAX_LENGTH.attrName(), XsdRestrictions::handleMaxLength,
            MIN_INCLUSIVE.attrName(), XsdRestrictions::handleMinInclusive,
            MAX_INCLUSIVE.attrName(), XsdRestrictions::handleMaxInclusive,
            ENUMERATION.attrName(), XsdRestrictions::handleEnumeration
    );

    public static Optional<XsdElementModel.Restriction> get(Element xsdElement) {
        return getXsdSimpleTypes(xsdElement)
                .flatMap(XsdRestrictions::extract)
                .map(XsdRestrictions::create);
    }

    // To get the first SimpleType element
    public static Optional<Element> getXsdSimpleTypes(Element element) {
        return toStream(element.getChildNodes())
                .filter(child -> SIMPLE_TYPE.tagName().equals(child.getLocalName()))
                .findFirst();
    }

    private static Optional<Element> extract(Element simpleType) {
        return toStream(simpleType.getChildNodes())
                .filter(child -> RESTRICTION.tagName().equals(child.getLocalName()))
                .findFirst();
    }

    private static XsdElementModel.Restriction create(Element restriction) {
        var model = new XsdElementModel.Restriction();
        setAttributeIfPresent(restriction, BASE.attrName(), Elements::stripPrefix, model::base);
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
        setAttributeIfPresent(element, VALUE.attrName(), Function.identity(), restriction::pattern);
    }

    private static void handleMinLength(Element element, XsdElementModel.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::minLength);
    }

    private static void handleMaxLength(Element element, XsdElementModel.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::maxLength);
    }

    private static void handleMinInclusive(Element element, XsdElementModel.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::minInclusive);
    }

    private static void handleMaxInclusive(Element element, XsdElementModel.Restriction restriction) {
        setAttributeIfPresent(element, VALUE.attrName(), Integer::valueOf, restriction::maxInclusive);
    }

    private static void handleEnumeration(Element element, XsdElementModel.Restriction restriction) {
        Optional.of(element.getAttribute(VALUE.attrName())).ifPresent(value -> {
            var enums = Optional.ofNullable(restriction.enumerations()).orElseGet(ArrayList::new);
            enums.add(value);
            restriction.enumerations(enums);
        });
    }
}
