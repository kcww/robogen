package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isWaitingEnableState;

@Service
public final class WaitUntilElementIsEnabledRule extends AbstractElementVerificationRule {

    WaitUntilElementIsEnabledRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_IS_ENABLED);
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return isWaitingEnableState(text);
    }
}
