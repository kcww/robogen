package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class InputTextRule extends AbstractElementActionRule {

    public InputTextRule() {
        super(SeleniumElementActionKeywordEnum.INPUT_TEXT);
    }
}
