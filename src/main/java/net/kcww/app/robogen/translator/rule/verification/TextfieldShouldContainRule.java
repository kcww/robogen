package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class TextfieldShouldContainRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.TEXTFIELD_SHOULD_CONTAIN;

    public TextfieldShouldContainRule() {
        super(KEYWORD);
    }
}
