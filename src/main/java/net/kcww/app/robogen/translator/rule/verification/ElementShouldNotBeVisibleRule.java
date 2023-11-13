package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class ElementShouldNotBeVisibleRule extends AbstractVerificationRule {

    ElementShouldNotBeVisibleRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_NOT_BE_VISIBLE);
    }
}
