package net.kcww.app.robogen.translator.rule.verification;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
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
    public boolean isApplicable(RelationModel relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.scenarioStep().text();
        return !Words.hasWait(stepText) &&
                Words.hasUrl(stepText);
    }

    @Override
    protected String getValue(RelationModel relation) {
        return TextMatcher.extractUrls(relation.scenarioStep().text()).stream()
                .findFirst().orElse(Words.ROBOT_EMPTY);
    }
}
