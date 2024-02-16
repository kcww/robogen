package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Keywords;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Waits until the current URL is expected.
@Service
public final class WaitUntilLocationIsRule extends AbstractNonElementRule {

    public WaitUntilLocationIsRule() {
        super(SeleniumKeywordEnum.WAIT_UNTIL_LOCATION_IS);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.parsedStep().text();
        return Words.hasWait(stepText) &&
                Words.hasUrl(stepText);
    }

    @Override
    public Keyword translate(StepRelation relation) {
        return buildKeyword(relation, Keywords::determineUrlArgument);
    }
}
