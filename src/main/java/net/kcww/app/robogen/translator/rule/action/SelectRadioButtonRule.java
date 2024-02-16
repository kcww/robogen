package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class SelectRadioButtonRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.SELECT_RADIO_BUTTON;

    public SelectRadioButtonRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasSelection(relation.parsedStep().text());
    }
}

// Select Radio Button: [GWT_RADIO_BUTTON, GWT_SIMPLE_RADIO_BUTTON, VAADIN_RADIO_GROUP]
// SELECT_RADIO_BUTTON("Select Radio Button", ACTION, hasLocAndArg()),  // group_name (locator), value

// isApplicable: xmlElement().tagName(), Words.hasSelection()
// translate: keyword, {group_name, value}

// And I select <smokingPref>
// And I select "No" for smokingPref

// <g:RadioButton name="smokingPref" value="Yes" />
// <g:SimpleRadioButton name="smokingPref" value="No" />
// <a:RadioGroup id="smokingPref" />