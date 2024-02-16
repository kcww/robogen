package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class InputPasswordRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.INPUT_PASSWORD;

    public InputPasswordRule() {
        super(SELENIUM_KEYWORD);
    }
}

// Input Password: [GWT_PASSWORD_TEXT_BOX, VAADIN_PASSWORD_FIELD]
// INPUT_PASSWORD("Input Password", ACTION, hasLocAndArg()),    // locator, password, clear = true (Ignored)

// isApplicable: xmlElement().tagName()
// translate: keyword, {locator, password}

// And I input "P@ssw0rd" into firstPass
// And I input "p@ssw0rD" into sec-Pass
// And I input <truPass>
// And I input <repeated-pass>

// <g:PasswordTextBox debugId="firstPass" />
// <a:PasswordField id="sec-Pass" />
// <g:PasswordTextBox debugId="truPass" />
// <a:PasswordField id="rep-pass" />