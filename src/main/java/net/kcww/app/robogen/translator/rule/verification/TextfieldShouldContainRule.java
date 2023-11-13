package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class TextfieldShouldContainRule extends AbstractVerificationRule {

    TextfieldShouldContainRule() {
        super(SeleniumElementVerificationKeywordEnum.TEXTFIELD_SHOULD_CONTAIN);
    }
}