package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Waits until text appears on the current page.
@Service
public final class WaitUntilPageContainsRule extends AbstractNonElementRule {

    public WaitUntilPageContainsRule() {
        super(SeleniumKeywordEnum.WAIT_UNTIL_PAGE_CONTAINS);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.scenarioStep().text();
        return Words.hasWait(stepText) && Words.hasPage(stepText);
    }
}
