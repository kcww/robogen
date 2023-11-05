package net.kcww.app.robogen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum XsdElement {

  ELEMENT("element"),
  SIMPLE_TYPE("simpleType"),
  RESTRICTION("restriction");

  private final String name;
}
