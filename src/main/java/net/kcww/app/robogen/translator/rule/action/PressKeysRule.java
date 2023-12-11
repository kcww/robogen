package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class PressKeysRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.PRESS_KEYS;

    public PressKeysRule() {
        super(KEYWORD);
    }
}
