package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.rule.KeywordRule;
import net.kcww.app.robogen.translator.rule.ActionableRule;
import net.kcww.app.robogen.mapper.model.RelationModel;
import org.springframework.stereotype.Service;

@Service
public class SelectCheckboxRule implements KeywordRule<RelationModel, KeywordModel>, ActionableRule {
  
  @Override
  public boolean isApplicable(RelationModel model) {
    return false;
  }

  @Override
  public KeywordModel translate(RelationModel model) {
    return null;
  }
}