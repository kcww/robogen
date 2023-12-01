package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class RadioButtonShouldBeSetToRule extends AbstractElementVerificationRule {

    RadioButtonShouldBeSetToRule() {
        super(SeleniumElementVerificationKeywordEnum.RADIO_BUTTON_SHOULD_BE_SET_TO);
    }

    // Given <radio> is set to "value",         return true;
    // Given <radio> is not unset to "value",   return true;    -- weired :(
    // Given <radio> is unset,                  return false;
    // Given <radio> is set to none,            return false;
    // Given <radio> is not set to "value",     return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isSelectAction(text);
    }
}
