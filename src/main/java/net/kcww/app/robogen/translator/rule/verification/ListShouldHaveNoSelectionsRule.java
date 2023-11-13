package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class ListShouldHaveNoSelectionsRule extends AbstractVerificationRule {

    ListShouldHaveNoSelectionsRule() {
        super(SeleniumElementVerificationKeywordEnum.LIST_SHOULD_HAVE_NO_SELECTIONS);
    }
}