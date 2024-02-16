package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class SelectCheckboxRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.SELECT_CHECKBOX;

    public SelectCheckboxRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasSelection(relation.parsedStep().text());
    }
}

// Checkbox: [GWT_CHECK_BOX, GWT_SIMPLE_CHECK_BOX, VAADIN_CHECKBOX]
// SELECT_CHECKBOX("Select Checkbox", ACTION, hasLoc()),    // locator

// isApplicable: xmlElement().tagName(), Words.hasSelection()
// translate: keyword, {locator}

// And I select chkOne
// And I select chkTwo
// And I select chkThree

// <g:CheckBox debugId="chkOne">One</g:CheckBox>
// <g:SimpleCheckBox debugId="chkTwo">Two</g:SimpleCheckBox>
// <a:Checkbox debugId="chkThree">Three</a:Checkbox>