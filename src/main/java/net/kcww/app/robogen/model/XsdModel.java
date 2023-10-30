package net.kcww.app.robogen.model;

import lombok.Data;

import java.util.List;

@Data
public class XsdModel {
  private String name;
  private String type;
  private int minOccurs;
  private Restriction restriction;

  @Data
  public static class Restriction {
    private String base;
    private String pattern;
    private int minLength;
    private int maxLength;
    private int minInclusive;
    private int maxInclusive;
    private List<String> enumerations;
  }
}
