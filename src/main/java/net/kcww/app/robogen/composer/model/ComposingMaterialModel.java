package net.kcww.app.robogen.composer.model;

import lombok.Builder;
import lombok.Data;
import net.kcww.app.robogen.translator.model.KeywordModel;

import java.util.List;

@Data
@Builder
public class ComposingMaterialModel {

  private String url;
  private String featureName;
  private String featureDescription;
  private String scenarioName;
  private List<KeywordModel> keywordModels;
}
