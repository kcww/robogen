package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class ClickButtonRule extends AbstractElementActionRule {

    public ClickButtonRule() {
        super(SeleniumElementActionKeywordEnum.CLICK_BUTTON);
    }
}
