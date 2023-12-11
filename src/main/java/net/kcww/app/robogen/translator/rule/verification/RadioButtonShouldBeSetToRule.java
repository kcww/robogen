package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class RadioButtonShouldBeSetToRule extends AbstractElementRule {

    public static final SeleniumKeyword KEYWORD = SeleniumKeywordEnum.RADIO_BUTTON_SHOULD_BE_SET_TO;

    public RadioButtonShouldBeSetToRule() {
        super(KEYWORD);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        return super.isApplicable(relation) && Words.hasSelection(relation.scenarioStep().text());
    }
}
