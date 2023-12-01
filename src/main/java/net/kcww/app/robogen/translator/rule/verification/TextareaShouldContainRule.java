package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class TextareaShouldContainRule extends AbstractElementVerificationRule {

    TextareaShouldContainRule() {
        super(SeleniumElementVerificationKeywordEnum.TEXTAREA_SHOULD_CONTAIN);
    }
}
