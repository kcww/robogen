package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isEnableState;

@Service
public class ElementShouldBeEnabledRule extends AbstractElementVerificationRule {

    ElementShouldBeEnabledRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_ENABLED);
    }

    // Given <element> is enabled,          return true;
    // Given <element> is not disabled,     return true;
    // Given <element> is disabled,         return false;
    // Given <element> is not enabled,      return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return isEnableState(text);
    }
}
