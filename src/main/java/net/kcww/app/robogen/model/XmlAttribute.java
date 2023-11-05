package net.kcww.app.robogen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum XmlAttribute {

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