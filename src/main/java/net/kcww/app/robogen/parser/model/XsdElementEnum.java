package net.kcww.app.robogen.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum XsdElementEnum {

    ELEMENT("element"),
    SIMPLE_TYPE("simpleType"),
    RESTRICTION("restriction");

    private final String tagName;
}
