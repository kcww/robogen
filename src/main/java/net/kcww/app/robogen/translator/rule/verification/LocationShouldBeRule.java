package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumVerificationKeywordEnum;
import org.springframework.stereotype.Service;

// Verifies that the current URL is exactly url.
@Service
public final class LocationShouldBeRule extends AbstractVerificationRule {

    LocationShouldBeRule() {
        super(SeleniumVerificationKeywordEnum.LOCATION_SHOULD_BE);
    }

    @Override
    public boolean isApplicable(RelationModel relationModel) {
        return false;
    }

    @Override
    public KeywordModel translate(RelationModel relationModel) {
        return null;
    }
}
