package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Waits until the current URL is expected.
@Service
public final class WaitUntilLocationIsRule extends AbstractNonElementRule {

    public WaitUntilLocationIsRule() {
        super(SeleniumKeywordEnum.WAIT_UNTIL_LOCATION_IS);
    }
    @Override
    public boolean isApplicable(RelationModel relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.scenarioStep().text();
        return Words.hasWait(stepText) && Words.hasUrl(stepText);
    }

    @Override
    protected String getValue(RelationModel relation) {
        return TextMatcher.extractUrls(relation.scenarioStep().text()).stream()
                .findFirst().orElse(Words.ROBOT_EMPTY);
    }
}
