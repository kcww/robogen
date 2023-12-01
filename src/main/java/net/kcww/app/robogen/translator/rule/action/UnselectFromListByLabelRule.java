package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class UnselectFromListByLabelRule extends AbstractElementActionRule {

    public UnselectFromListByLabelRule() {
        super(SeleniumElementActionKeywordEnum.UNSELECT_FROM_LIST_BY_LABEL);
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isUnselectAction(text);
    }
}
