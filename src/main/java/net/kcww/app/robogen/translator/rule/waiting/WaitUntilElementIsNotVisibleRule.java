package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isWaitingInvisibleState;

@Service
public final class WaitUntilElementIsNotVisibleRule extends AbstractElementVerificationRule {

    WaitUntilElementIsNotVisibleRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE);
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return isWaitingInvisibleState(text);
    }
}
