package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class WaitUntilElementContainsRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS;

    public WaitUntilElementContainsRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasWait(relation.parsedStep().text());
    }
}