package net.kcww.app.robogen.translator.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.rule.KeywordRule;
import net.kcww.app.robogen.translator.rule.fallback.FallbackRule;
import net.kcww.app.robogen.translator.service.TranslatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GherkinToRobotTranslatorServiceImpl implements TranslatorService<RelationModel, KeywordModel> {

    private static final FallbackRule FALLBACK_RULE = new FallbackRule();
    private final List<? extends KeywordRule<RelationModel, KeywordModel>> rules;

    /**
     * Translates a single RelationModel to a KeywordModel.
     *
     * @param relationModel The RelationModel to be translated.
     * @return Translated KeywordModel.
     */
    @Override
    public KeywordModel translate(RelationModel relationModel) {
        return rules.stream()
                .filter(rule -> rule.isApplicable(relationModel))
                .findFirst()
                .map(rule -> rule.translate(relationModel))
                .orElseGet(() -> FALLBACK_RULE.translate(relationModel));
    }

    /**
     * Translates a list of RelationModels to a list of KeywordModels.
     *
     * @param models The list of RelationModels to be translated.
     * @return List of translated KeywordModels.
     */
    @Override
    public List<KeywordModel> translate(List<RelationModel> models) {
        return models.stream().map(this::translate).collect(Collectors.toList());
    }
}
