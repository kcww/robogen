package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class TextfieldShouldContainRule extends AbstractElementVerificationRule {

    TextfieldShouldContainRule() {
        super(SeleniumElementVerificationKeywordEnum.TEXTFIELD_SHOULD_CONTAIN);
    }
}
