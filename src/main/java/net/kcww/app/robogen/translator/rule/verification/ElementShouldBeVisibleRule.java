package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isVisibleState;

@Service
public final class ElementShouldBeVisibleRule extends AbstractElementVerificationRule {

    ElementShouldBeVisibleRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_VISIBLE);
    }

    // Given <element> is visible,          return true;
    // Given <element> is not invisible,    return true;
    // Given <element> is invisible,        return false;
    // Given <element> is not visible,      return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return isVisibleState(text);
    }
}
