package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class RadioButtonShouldNotBeSelectedRule extends AbstractVerificationRule {

    RadioButtonShouldNotBeSelectedRule() {
        super(SeleniumElementVerificationKeywordEnum.RADIO_BUTTON_SHOULD_NOT_BE_SELECTED);
    }
}