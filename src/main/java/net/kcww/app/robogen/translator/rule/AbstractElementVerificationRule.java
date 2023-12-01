package net.kcww.app.robogen.translator.rule;

import io.cucumber.messages.types.StepKeywordType;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;

@Slf4j
public abstract class AbstractElementVerificationRule extends AbstractElementRule {

    protected AbstractElementVerificationRule(SeleniumKeyword seleniumKeyword) {
        super(seleniumKeyword);
    }

    @Override
    protected boolean matchesStepKeywordType(StepKeywordType type) {
        return type == StepKeywordType.CONTEXT || type == StepKeywordType.OUTCOME;
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return true;
    }
}
