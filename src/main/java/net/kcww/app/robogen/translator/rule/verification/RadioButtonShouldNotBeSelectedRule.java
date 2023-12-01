package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class RadioButtonShouldNotBeSelectedRule extends AbstractElementVerificationRule {

    RadioButtonShouldNotBeSelectedRule() {
        super(SeleniumElementVerificationKeywordEnum.RADIO_BUTTON_SHOULD_NOT_BE_SELECTED);
    }

    // Given <radio> is unset,                  return true;
    // Given <radio> is not set to "value",     return true;
    // Given None of <radio> is set,            return true;
    // Given <radio> is set to "value",         return false;
    // Given <radio> is not unset to "value",   return false;    -- weired :(
    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isUnselectAction(text);
    }
}
