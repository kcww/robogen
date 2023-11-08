package net.kcww.app.robogen.translator.rule.validation;

import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.rule.KeywordRule;
import net.kcww.app.robogen.translator.rule.ValidatableRule;
import net.kcww.app.robogen.mapper.model.RelationModel;
import org.springframework.stereotype.Service;

@Service
public class WaitUntilElementIsNotVisibleRule implements KeywordRule<RelationModel, KeywordModel>, ValidatableRule {
  
  @Override
  public boolean isApplicable(RelationModel model) {
    return false;
  }

  @Override
  public KeywordModel translate(RelationModel model) {
    return null;
  }
}