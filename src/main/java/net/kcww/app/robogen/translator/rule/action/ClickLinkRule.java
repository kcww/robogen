package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ClickLinkRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.CLICK_LINK;

    public ClickLinkRule() {
        super(SELENIUM_KEYWORD);
    }
}

// Click Link: [GWT_ANCHOR]
// CLICK_LINK("Click Link", ACTION, hasLoc()),      // locator, modifier = false (Ignored)

// isApplicable: xmlElement().tagName()
// translate: keyword, {locator}

// And I click lnkOne

// <g:Anchor debugId="lnkOne">One</g:Anchor>