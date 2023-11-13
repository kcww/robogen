package net.kcww.app.robogen.parser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum XsdElementEnum {

    ELEMENT("element"),
    SIMPLE_TYPE("simpleType"),
    RESTRICTION("restriction");

    private final String name;
}
