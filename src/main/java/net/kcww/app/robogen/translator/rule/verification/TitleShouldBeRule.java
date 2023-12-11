package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRule;
import org.springframework.stereotype.Service;

// Verifies that the current page title equals title.
@Service
public final class TitleShouldBeRule extends AbstractNonElementRule {

    public TitleShouldBeRule() {
        super(SeleniumKeywordEnum.TITLE_SHOULD_BE);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.scenarioStep().text();
        return !Words.hasWait(stepText) &&
                Words.hasTitle(stepText);
    }
}
