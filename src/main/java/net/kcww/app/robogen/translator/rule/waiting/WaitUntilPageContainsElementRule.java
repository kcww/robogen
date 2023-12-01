package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isWaitingState;

// Waits until the element locator appears on the current page.
@Service
public final class WaitUntilPageContainsElementRule extends AbstractElementVerificationRule {

    WaitUntilPageContainsElementRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_PAGE_CONTAINS_ELEMENT);
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        if (!isWaitingState(text)) return false;
        return TextMatcher.containsPattern(text, Tokens.PAGE_PATTERNS);
    }
}