package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class UnselectAllFromListRule extends AbstractElementActionRule {

    public UnselectAllFromListRule() {
        super(SeleniumElementActionKeywordEnum.UNSELECT_ALL_FROM_LIST);
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isUnselectAllAction(text);
    }
}
