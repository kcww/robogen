package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumVerificationKeywordEnum;
import org.springframework.stereotype.Service;

// Verifies that the current page title equals title.
@Service
public final class TitleShouldBeRule extends AbstractVerificationRule {

    TitleShouldBeRule() {
        super(SeleniumVerificationKeywordEnum.TITLE_SHOULD_BE);
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
