package net.kcww.app.robogen.parser.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum XmlAttributeEnum {

    ID("id"),
    NAME("name"),
    LABEL("label"),
    VALUE("value"),
    MIN("min"),
    MAX("max"),
    REQUIRED("required");

    private final String attrName;
}
