package net.kcww.app.robogen.translator.rule.fallback;

import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.rule.KeywordRule;
import net.kcww.app.robogen.mapper.model.RelationModel;
import org.springframework.stereotype.Service;

@Service
@Priority(Integer.MAX_VALUE)
@Slf4j
public final class FallbackRule implements KeywordRule<RelationModel, KeywordModel> {

  @Override
  public boolean isApplicable(RelationModel model) {
    return true;
  }

  @Override
  public KeywordModel translate(RelationModel model) {
    var stepText = model.getStep().getText();
    var errorMessage = String.format("No Keyword found for Step: %s", stepText);
    log.info(errorMessage);
    return KeywordModel.builder()
      .keyword(errorMessage)
      .build();
  }
}
