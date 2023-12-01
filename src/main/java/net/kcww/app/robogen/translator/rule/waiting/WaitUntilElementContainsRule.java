package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isWaitingState;

@Service
public final class WaitUntilElementContainsRule extends AbstractElementVerificationRule {

    WaitUntilElementContainsRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
    }

    protected boolean matchesTokenCondition(String text) {
        return isWaitingState(text);
    }
}