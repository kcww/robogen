package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class UnselectCheckboxRule extends AbstractElementActionRule {

    public UnselectCheckboxRule() {
        super(SeleniumElementActionKeywordEnum.UNSELECT_CHECKBOX);
    }

    // When I unselect <checkbox>
    // When I uncheck <checkbox>
    // When I do not check <checkbox>
    // When I don't check <checkbox>
    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isUnselectAction(text);
    }
}
