package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import org.springframework.stereotype.Service;

// Waits until the current URL is expected.
@Service
public final class WaitUntilLocationIsRule extends AbstractWaitingRule {

    WaitUntilLocationIsRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_LOCATION_IS);
    }
}
