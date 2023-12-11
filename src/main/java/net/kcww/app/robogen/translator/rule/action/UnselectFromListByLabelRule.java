package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class UnselectFromListByLabelRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.UNSELECT_FROM_LIST_BY_LABEL;

    public UnselectFromListByLabelRule() {
        super(KEYWORD);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        return super.isApplicable(relation) && Words.hasDeselection(relation.scenarioStep().text());
    }
}
