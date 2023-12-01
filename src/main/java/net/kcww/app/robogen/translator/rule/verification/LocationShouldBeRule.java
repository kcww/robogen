package net.kcww.app.robogen.translator.rule.verification;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Tokens;
import net.kcww.app.robogen.translator.model.selenium.SeleniumVerificationKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractVerificationRule;
import org.springframework.stereotype.Service;

// Verifies that the current URL is exactly url.
@Service
@Slf4j
public final class LocationShouldBeRule extends AbstractVerificationRule {

    LocationShouldBeRule() {
        super(SeleniumVerificationKeywordEnum.LOCATION_SHOULD_BE);
    }

    // Given I am on http://localhost:8080/room-booking
    // Given I am on http://www.example.com
    // Given I am on https://www.example.com
    // Given I am on www.example.com
    @Override
    protected boolean matchesTokenCondition(String text) {
        return TextMatcher.containsUrl(text);
    }

    @Override
    protected String getValue(RelationModel relation) {
        return TextMatcher.extractUrls(relation.scenarioStep().text()).stream()
                .findFirst().orElse(Tokens.ROBOT_EMPTY);
    }
}
