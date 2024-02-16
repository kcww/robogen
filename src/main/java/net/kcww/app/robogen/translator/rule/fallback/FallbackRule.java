package net.kcww.app.robogen.translator.rule.fallback;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.rule.KeywordRule;

//@Service
//@Priority(Integer.MAX_VALUE)
@Slf4j
public final class FallbackRule implements KeywordRule<StepRelation, Keyword> {

    /**
     * Always returns true as this is a fallback rule.
     *
     * @param model The RelationModel to be checked for applicability.
     * @return true in all cases.
     */
    @Override
    public boolean isApplicable(StepRelation model) {
        return true;
    }

    /**
     * Translates the given RelationModel to a KeywordModel containing an error message.
     * This method is used as a fallback when no other rules apply.
     *
     * @param model The RelationModel to be translated.
     * @return KeywordModel containing an error message.
     */
    @Override
    public Keyword translate(StepRelation model) {
        var stepText = model.parsedStep().text();
        var errorMessage = String.format("No Keyword found for Step: %s", stepText);
        log.info(errorMessage);
        return Keyword.builder().keyword(errorMessage).build();
    }
}
