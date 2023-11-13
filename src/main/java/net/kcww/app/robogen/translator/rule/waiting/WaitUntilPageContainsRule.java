package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import org.springframework.stereotype.Service;

// Waits until text appears on the current page.
@Service
public final class WaitUntilPageContainsRule extends AbstractWaitingRule {

    WaitUntilPageContainsRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_PAGE_CONTAINS);
    }
}
