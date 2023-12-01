package net.kcww.app.robogen.translator.rule;

import io.cucumber.messages.types.StepKeywordType;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;

public abstract class AbstractElementActionRule extends AbstractElementRule {

    protected AbstractElementActionRule(SeleniumKeyword seleniumKeyword) {
        super(seleniumKeyword);
    }

    @Override
    protected boolean matchesStepKeywordType(StepKeywordType type) {
        return type == StepKeywordType.ACTION;
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return true;
    }
}
