package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class TextareaShouldContainRule extends AbstractVerificationRule {

    TextareaShouldContainRule() {
        super(SeleniumElementVerificationKeywordEnum.TEXTAREA_SHOULD_CONTAIN);
    }
}
