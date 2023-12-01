package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class CheckboxShouldBeSelectedRule extends AbstractElementVerificationRule {

    CheckboxShouldBeSelectedRule() {
        super(SeleniumElementVerificationKeywordEnum.CHECK_BOX_SHOULD_BE_SELECTED);
    }

    // Given <checkbox> is checked,          return true;
    // Given <checkbox> is not unchecked,    return true;
    // Given <checkbox> is unchecked,        return false;
    // Given <checkbox> is not checked,      return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isSelectAction(text);
    }
}
