package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isDisableState;

@Service
public final class ElementShouldBeDisabledRule extends AbstractElementVerificationRule {

    ElementShouldBeDisabledRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_DISABLED);
    }

    // Given <element> is disabled,         return true;
    // Given <element> is not enabled,      return true;
    // Given <element> is enabled,          return false;
    // Given <element> is not disabled,     return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return isDisableState(text);
    }
}
