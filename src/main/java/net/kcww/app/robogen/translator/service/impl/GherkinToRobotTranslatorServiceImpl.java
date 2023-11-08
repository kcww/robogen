package net.kcww.app.robogen.translator.service.impl;

import lombok.AllArgsConstructor;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.rule.KeywordRule;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.service.TranslatorService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GherkinToRobotTranslatorServiceImpl implements TranslatorService<RelationModel, KeywordModel> {

  private final List<KeywordRule<RelationModel, KeywordModel>> rules;

  @Override
  public KeywordModel translate(RelationModel model) {
    return rules.stream()
      .filter(rule -> rule.isApplicable(model))
      .findFirst()
      .map(rule -> rule.translate(model))
      .get();
  }

  @Override
  public List<KeywordModel> translate(List<RelationModel> models) {
    return models.stream()
      .sorted(Comparator.comparing(model -> model.getStep().getLocation().getLine()))
      .map(this::translate)
      .collect(Collectors.toList());
  }
}
