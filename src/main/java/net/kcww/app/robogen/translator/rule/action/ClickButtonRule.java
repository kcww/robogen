package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ClickButtonRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.CLICK_BUTTON;

    public ClickButtonRule() {
        super(SELENIUM_KEYWORD);
    }
}

// Click Button: [  GWT_BUTTON, GWT_PUSH_BUTTON, GWT_RESET_BUTTON, GWT_SUBMIT_BUTTON, GWT_TOGGLE_BUTTON,
//                  VAADIN_BUTTON ]                     // The VAADIN_BUTTON was removed because it has the same tagName as GWT_BUTTON.
// CLICK_BUTTON("Click Button", ACTION, hasLoc()),      // locator, modifier = false (Ignored)

// isApplicable: xmlElement().tagName()
// translate: keyword, {locator}

// And I click btnOne
// And I click btnTwo
// And I click btnThree
// And I click btnFour
// And I click btnFive
// And I click btnSix

// <g:Button debugId="btnOne">One</g:Button>
// <g:PushButton debugId="btnTwo">Two</g:PushButton>
// <g:ResetButton debugId="btnThree">Three</g:ResetButton>
// <g:SubmitButton debugId="btnFour">Four</g:SubmitButton>
// <g:ToggleButton debugId="btnFive">Five</g:ToggleButton>
// <a:Button id="btnSix">Six</a:Button>