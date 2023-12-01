package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractVerificationRule;
import org.springframework.stereotype.Service;

// Verifies that current page contains text.
@Service
public final class PageShouldContainRule extends AbstractVerificationRule {

    PageShouldContainRule() {
        super(SeleniumVerificationKeywordEnum.PAGE_SHOULD_CONTAIN);
    }

    // Given the page contains "Hotel Booking" header
    @Override
    protected boolean matchesTokenCondition(String text) {
        return TextMatcher.containsPattern(text, Tokens.PAGE_PATTERNS);
    }
}
