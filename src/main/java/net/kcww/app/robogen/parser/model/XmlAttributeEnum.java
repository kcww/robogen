package net.kcww.app.robogen.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum XmlAttributeEnum {

    ID("id"),
    DEBUG_ID("debugId"),
    NAME("name"),
    LABEL("label"),
    VALUE("value"),
    MIN("min"),
    MAX("max"),
    REQUIRED("required");

    private final String name;

}