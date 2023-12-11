package net.kcww.app.robogen.translator.rule.waiting;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class WaitUntilElementIsVisibleRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_IS_VISIBLE;

    public WaitUntilElementIsVisibleRule() {
        super(KEYWORD);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (!super.isApplicable(relation)) return false;
        var stepText = relation.scenarioStep().text();
        return Words.hasWait(stepText) && Words.hasVisible(stepText);
    }
}
