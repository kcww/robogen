package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class ElementShouldBeDisabledRule extends AbstractVerificationRule {

    ElementShouldBeDisabledRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_DISABLED);
    }
}
