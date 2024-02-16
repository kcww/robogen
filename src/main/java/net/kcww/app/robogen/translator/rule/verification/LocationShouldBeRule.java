package net.kcww.app.robogen.translator.rule.verification;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Keywords;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Verifies that the current URL is exactly url.
@Service
@Slf4j
public final class LocationShouldBeRule extends AbstractNonElementRule {

    public LocationShouldBeRule() {
        super(SeleniumKeywordEnum.LOCATION_SHOULD_BE);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.parsedStep().text();
        return !Words.hasWait(stepText) &&
                Words.hasUrl(stepText);
    }

    @Override
    public Keyword translate(StepRelation relation) {
        return buildKeyword(relation, Keywords::determineUrlArgument);
    }
}
