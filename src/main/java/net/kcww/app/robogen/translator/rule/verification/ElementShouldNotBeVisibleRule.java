package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ElementShouldNotBeVisibleRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.ELEMENT_SHOULD_NOT_BE_VISIBLE;

    public ElementShouldNotBeVisibleRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasInvisible(relation.parsedStep().text());
    }
}
