package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class WaitUntilElementIsVisibleRule extends AbstractWaitingRule {

    WaitUntilElementIsVisibleRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_IS_VISIBLE);
    }
}
