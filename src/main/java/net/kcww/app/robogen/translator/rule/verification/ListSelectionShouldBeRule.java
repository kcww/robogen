package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementVerificationRule;
import org.springframework.stereotype.Service;

@Service
public final class ListSelectionShouldBeRule extends AbstractElementVerificationRule {

    ListSelectionShouldBeRule() {
        super(SeleniumElementVerificationKeywordEnum.LIST_SELECTION_SHOULD_BE);
    }

    // Given <list> is selected to "Single",         return true;
    // Given <list> is not unselected to "Single",   return true;    -- weired :(
    // Given <list> is unselected,                   return false;
    // Given <list> is not selected,                 return false;
    // Given none of <list> is selected,             return false;
    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isSelectAction(text);
    }
}
