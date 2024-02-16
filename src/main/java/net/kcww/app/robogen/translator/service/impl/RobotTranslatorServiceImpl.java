package net.kcww.app.robogen.translator.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.rule.KeywordRule;
import net.kcww.app.robogen.translator.rule.fallback.FallbackRule;
import net.kcww.app.robogen.translator.service.TranslatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RobotTranslatorServiceImpl implements TranslatorService<StepRelation, Keyword> {

    private static final FallbackRule FALLBACK_RULE = new FallbackRule();
    private final List<? extends KeywordRule<StepRelation, Keyword>> rules;

    /**
     * Translates a single RelationModel to a KeywordModel.
     *
     * @param relation The RelationModel to be translated.
     * @return Translated KeywordModel.
     */
    @Override
    public Keyword translate(StepRelation relation) {
        return rules.stream()
                .filter(rule -> rule.isApplicable(relation))
                // TODO: Multiple rules matched case.
                .findFirst()
                .map(rule -> rule.translate(relation))
                .orElseGet(() -> FALLBACK_RULE.translate(relation));
    }

    /**
     * Translates a list of RelationModels to a list of KeywordModels.
     *
     * @param relations The list of RelationModels to be translated.
     * @return List of translated KeywordModels.
     */
    @Override
    public List<Keyword> translate(List<StepRelation> relations) {
        return relations.stream().map(this::translate).collect(Collectors.toList());
    }
}
