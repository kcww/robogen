package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class SelectRadioButtonRule extends AbstractElementRule {

    public SelectRadioButtonRule() {
        super(SeleniumElementActionKeywordEnum.SELECT_RADIO_BUTTON);
    }
}
