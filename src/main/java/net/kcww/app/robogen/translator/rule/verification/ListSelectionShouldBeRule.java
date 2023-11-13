package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class ListSelectionShouldBeRule extends AbstractVerificationRule {

    ListSelectionShouldBeRule() {
        super(SeleniumElementVerificationKeywordEnum.LIST_SELECTION_SHOULD_BE);
    }
}