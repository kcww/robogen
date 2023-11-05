package net.kcww.app.robogen.model;

import lombok.Data;

@Data
public class XmlElementModel {

  // XML Element name
  private String tag;

  // XML Attributes
  private String id;
  private String name;
  private String label;
  private String value;
  private String min;
  private String max;
  private boolean required;
}
