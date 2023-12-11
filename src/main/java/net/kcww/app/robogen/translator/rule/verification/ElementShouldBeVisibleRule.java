package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ElementShouldBeVisibleRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.ELEMENT_SHOULD_BE_VISIBLE;

    public ElementShouldBeVisibleRule() {
        super(KEYWORD);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        return super.isApplicable(relation) && Words.hasVisible(relation.scenarioStep().text());
    }
}
