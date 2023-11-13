package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class PressKeysRule extends AbstractElementRule {

    public PressKeysRule() {
        super(SeleniumElementActionKeywordEnum.PRESS_KEYS);
    }
}
