package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ClickImageRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.CLICK_IMAGE;

    public ClickImageRule() {
        super(SELENIUM_KEYWORD);
    }
}

// Click Image: [GWT_IMAGE, VAADIN_IMAGE]           // The VAADIN_IMAGE was removed because it has the same tagName as GWT_IMAGE.
// CLICK_IMAGE("Click Image", ACTION, hasLoc()),    // locator, modifier = false (Ignored)

// isApplicable: xmlElement().tagName()
// translate: keyword, {locator}

// And I click imgOne
// And I click imgTwo

// <g:Image debugId="imgOne">Five</g:Image>
// <a:Image id="imgTwo">Six</a:Image>