package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class TextareaShouldContainRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.TEXTAREA_SHOULD_CONTAIN;

    public TextareaShouldContainRule() {
        super(SELENIUM_KEYWORD);
    }
}
