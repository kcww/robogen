package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import org.springframework.stereotype.Service;

// Waits until the element locator appears on the current page.
@Service
public final class WaitUntilPageContainsElementRule extends AbstractWaitingRule {

    WaitUntilPageContainsElementRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_PAGE_CONTAINS_ELEMENT);
    }
}