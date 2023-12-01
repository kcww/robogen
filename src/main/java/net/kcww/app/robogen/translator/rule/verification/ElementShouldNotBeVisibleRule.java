package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isInvisibleState;

@Service
public final class ElementShouldNotBeVisibleRule extends AbstractElementVerificationRule {

    ElementShouldNotBeVisibleRule() {
        super(SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_NOT_BE_VISIBLE);
    }

    // Given <element> is invisible,        return true;
    // Given <element> is not visible,      return true;
    // Given <element> is visible,          return false;
    // Given <element> is not invisible,    return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return isInvisibleState(text);
    }
}
