package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Verifies that the current page title equals title.
@Service
public final class TitleShouldBeRule extends AbstractNonElementRule {

    public TitleShouldBeRule() {
        super(SeleniumKeywordEnum.TITLE_SHOULD_BE);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.parsedStep().text();
        return !Words.hasWait(stepText) &&
                Words.hasTitle(stepText);
    }
}
