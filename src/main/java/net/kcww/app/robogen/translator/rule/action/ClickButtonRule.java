package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class ClickButtonRule extends AbstractElementRule {

    public ClickButtonRule() {
        super(SeleniumElementActionKeywordEnum.CLICK_BUTTON);
    }
}
