package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class InputPasswordRule extends AbstractElementActionRule {

    public InputPasswordRule() {
        super(SeleniumElementActionKeywordEnum.INPUT_PASSWORD);
    }
}
