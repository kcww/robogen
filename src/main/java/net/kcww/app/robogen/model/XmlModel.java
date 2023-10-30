package net.kcww.app.robogen.model;

import lombok.Data;

@Data
public class XmlModel {
  private String tag;
  private String id;
  private String name;
  private String label;
  private String value;
  private int min;
  private int max;
  private boolean required;
}
