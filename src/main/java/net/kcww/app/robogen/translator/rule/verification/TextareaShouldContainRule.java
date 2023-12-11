package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class TextareaShouldContainRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.TEXTAREA_SHOULD_CONTAIN;

    public TextareaShouldContainRule() {
        super(KEYWORD);
    }
}
