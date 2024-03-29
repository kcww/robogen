package net.kcww.app.robogen.parser.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum XsdAttributeEnum {

    NAME("name"),
    TYPE("type"),
    NILLABLE("nillable"),
    MIN_OCCURS("minOccurs"),
    MAX_OCCURS("maxOccurs"),

    BASE("base"),
    PATTERN("pattern"),
    MIN_LENGTH("minLength"),
    MAX_LENGTH("maxLength"),
    MIN_INCLUSIVE("minInclusive"),
    MAX_INCLUSIVE("maxInclusive"),
    ENUMERATION("enumeration"),
    VALUE("value");

    private final String attrName;
}