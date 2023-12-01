package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractVerificationRule;
import org.springframework.stereotype.Service;

// Verifies that the current page title equals title.
@Service
public final class TitleShouldBeRule extends AbstractVerificationRule {

    TitleShouldBeRule() {
        super(SeleniumVerificationKeywordEnum.TITLE_SHOULD_BE);
    }

    // Given the title is "Hotel Booking"
    @Override
    protected boolean matchesTokenCondition(String text) {
        return TextMatcher.containsPattern(text, Tokens.TITLE_PATTERNS) &&
                TextMatcher.containsPattern(text, Tokens.BE_PATTERNS);
    }
}
