package net.kcww.app.robogen.translator.rule;

import io.cucumber.messages.types.StepKeywordType;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.rule.KeywordRule;

public abstract class AbstractVerificationRule implements KeywordRule<RelationModel, KeywordModel> {

    protected final SeleniumKeyword seleniumKeyword;

    protected AbstractVerificationRule(SeleniumKeyword seleniumKeyword) {
        this.seleniumKeyword = seleniumKeyword;
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (relation.xmlElement() != null) return false;

        var scenarioStepType = relation.scenarioStep().type();
        if (scenarioStepType != StepKeywordType.CONTEXT && scenarioStepType != StepKeywordType.OUTCOME) return false;

        return matchesTokenCondition(relation.scenarioStep().text());
    }

    protected boolean matchesTokenCondition(String text) {
        return true;
    };

    @Override
    public KeywordModel translate(RelationModel relation) {
        var builder = KeywordModel.builder().keyword(seleniumKeyword.keyword());
        if (seleniumKeyword.hasArgument()) {
            builder.value(getValue(relation));
        }
        return builder.build();
    }

    protected String getValue(RelationModel relation) {
        var literals = relation.scenarioStep().literals();
        return !literals.isEmpty() ? literals.get(0) : Tokens.ROBOT_EMPTY;
    }
}
