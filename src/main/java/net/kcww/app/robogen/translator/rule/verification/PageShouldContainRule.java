package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Verifies that current page contains text.
@Service
public final class PageShouldContainRule extends AbstractNonElementRule {

    public PageShouldContainRule() {
        super(SeleniumKeywordEnum.PAGE_SHOULD_CONTAIN);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.parsedStep().text();
        return !Words.hasWait(stepText) &&
                Words.hasPage(stepText);
    }
}
