package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ChooseFileRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.CHOOSE_FILE;

    public ChooseFileRule() {
        super(KEYWORD);
    }
}
