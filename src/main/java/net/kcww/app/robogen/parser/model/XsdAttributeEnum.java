package net.kcww.app.robogen.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
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

    private final String name;
}