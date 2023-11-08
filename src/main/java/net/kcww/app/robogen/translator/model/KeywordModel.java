package net.kcww.app.robogen.translator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeywordModel {

  private String keyword;
  private String locator;
  private String argument;
}
