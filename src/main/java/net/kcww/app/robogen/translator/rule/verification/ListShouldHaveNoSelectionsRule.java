package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class ListShouldHaveNoSelectionsRule extends AbstractElementVerificationRule {

    ListShouldHaveNoSelectionsRule() {
        super(SeleniumElementVerificationKeywordEnum.LIST_SHOULD_HAVE_NO_SELECTIONS);
    }

    // Given <list> is unselected,                   return true;
    // Given <list> is not selected,                 return true;
    // Given none of <list> is selected,             return true;
    // Given <list> is selected to "Single",         return false;
    // Given <list> is not unselected to "Single",   return false;    -- weired :(
    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isUnselectAction(text);
    }
}
