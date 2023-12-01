package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class SelectRadioButtonRule extends AbstractElementActionRule {

    public SelectRadioButtonRule() {
        super(SeleniumElementActionKeywordEnum.SELECT_RADIO_BUTTON);
    }

    @Override
    protected boolean matchesTokenCondition(String text) {
        return Tokens.isSelectAction(text);
    }
}
