package net.kcww.app.robogen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum XsdAttribute {

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