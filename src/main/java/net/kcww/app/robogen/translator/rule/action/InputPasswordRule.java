package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class InputPasswordRule extends AbstractElementRule {

    public InputPasswordRule() {
        super(SeleniumElementActionKeywordEnum.INPUT_PASSWORD);
    }
}
