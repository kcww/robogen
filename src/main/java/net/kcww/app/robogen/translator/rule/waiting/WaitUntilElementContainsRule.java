package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class WaitUntilElementContainsRule extends AbstractWaitingRule {

    WaitUntilElementContainsRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
    }
}