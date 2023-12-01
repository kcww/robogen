package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractVerificationRule;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.translator.helper.Tokens.isWaitingState;

// Waits until the current URL is expected.
@Service
public final class WaitUntilLocationIsRule extends AbstractVerificationRule {

    WaitUntilLocationIsRule() {
        super(SeleniumWaitingKeywordEnum.WAIT_UNTIL_LOCATION_IS);
    }

    protected boolean matchesTokenCondition(String text) {
        if (!isWaitingState(text)) return false;
        return TextMatcher.containsUrl(text);
    }

    @Override
    protected String getValue(RelationModel relation) {
        return TextMatcher.extractUrls(relation.scenarioStep().text()).stream()
                .findFirst().orElse(Tokens.ROBOT_EMPTY);
    }
}
