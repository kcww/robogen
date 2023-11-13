package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class UnselectCheckboxRule extends AbstractElementRule {

    public UnselectCheckboxRule() {
        super(SeleniumElementActionKeywordEnum.UNSELECT_CHECKBOX);
    }
}
