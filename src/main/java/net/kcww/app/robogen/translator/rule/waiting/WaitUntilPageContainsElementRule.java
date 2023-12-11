package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Waits until the element locator appears on the current page.
@Service
public final class WaitUntilPageContainsElementRule extends AbstractNonElementRule {

    public WaitUntilPageContainsElementRule() {
        super(SeleniumKeywordEnum.WAIT_UNTIL_PAGE_CONTAINS_ELEMENT);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (!super.isApplicable(relation)) return false;
        var stepParameters = relation.scenarioStep().parameters();
        if (stepParameters == null || stepParameters.isEmpty()) return false;
        var stepText = relation.scenarioStep().text();
        return Words.hasWait(stepText) && Words.hasPage(stepText);
    }
}