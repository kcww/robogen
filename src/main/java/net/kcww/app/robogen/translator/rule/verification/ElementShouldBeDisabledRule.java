package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ElementShouldBeDisabledRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.ELEMENT_SHOULD_BE_DISABLED;

    public ElementShouldBeDisabledRule() {
        super(KEYWORD);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        return super.isApplicable(relation) && Words.hasDisable(relation.scenarioStep().text());
    }
}
