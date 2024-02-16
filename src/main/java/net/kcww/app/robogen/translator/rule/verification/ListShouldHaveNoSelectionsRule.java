package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ListShouldHaveNoSelectionsRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.LIST_SHOULD_HAVE_NO_SELECTIONS;

    public ListShouldHaveNoSelectionsRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasDeselection(relation.parsedStep().text());
    }
}
