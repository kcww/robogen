package net.kcww.app.robogen.model;

import lombok.Data;

import java.util.List;

@Data
public class XsdElementModel {

  // XSD Attributes
  private String name;
  private String type;
  private boolean nillable = false;
  private int minOccurs = 1;
  private int maxOccurs = 1;

  // XSD Restriction element
  private Restriction restriction;

  @Data
  public static class Restriction {
    private String base;
    private String pattern;
    private int minLength = -1;
    private int maxLength = -1;
    private int minInclusive = -1;
    private int maxInclusive = -1;
    private List<String> enumerations;
  }
}
