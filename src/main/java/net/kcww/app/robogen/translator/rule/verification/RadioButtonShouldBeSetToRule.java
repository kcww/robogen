package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class RadioButtonShouldBeSetToRule extends AbstractVerificationRule {

    RadioButtonShouldBeSetToRule() {
        super(SeleniumElementVerificationKeywordEnum.RADIO_BUTTON_SHOULD_BE_SET_TO);
    }
}
