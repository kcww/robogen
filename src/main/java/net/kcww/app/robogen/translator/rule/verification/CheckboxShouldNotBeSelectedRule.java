package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class CheckboxShouldNotBeSelectedRule extends AbstractVerificationRule {

    CheckboxShouldNotBeSelectedRule() {
        super(SeleniumElementVerificationKeywordEnum.CHECK_BOX_SHOULD_NOT_BE_SELECTED);
    }
}