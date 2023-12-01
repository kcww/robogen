package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class PressKeysRule extends AbstractElementActionRule {

    public PressKeysRule() {
        super(SeleniumElementActionKeywordEnum.PRESS_KEYS);
    }
}
