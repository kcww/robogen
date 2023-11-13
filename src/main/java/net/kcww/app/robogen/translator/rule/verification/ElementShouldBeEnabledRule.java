package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public class ElementShouldBeEnabledRule extends AbstractVerificationRule {

    ElementShouldBeEnabledRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_ENABLED);
    }
}
