package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumVerificationKeywordEnum;
import org.springframework.stereotype.Service;

// Verifies that current page contains text.
@Service
public final class PageShouldContainRule extends AbstractVerificationRule {

    PageShouldContainRule() {
        super(SeleniumVerificationKeywordEnum.PAGE_SHOULD_CONTAIN);
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
