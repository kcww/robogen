package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class SelectAllFromListRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.SELECT_ALL_FROM_LIST;

    public SelectAllFromListRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasWholeSelection(relation.parsedStep().text());
    }
}

// Select All From List: [GWT_LIST_BOX, VAADIN_COMBO_BOX, VAADIN_SELECT]
// SELECT_ALL_FROM_LIST("Select All From List", ACTION, hasLoc()),  // locator

// isApplicable: xmlElement().tagName(), Words.hasWholeSelection()
// translate: keyword, {locator}

// And I select all from cat-select
// And I select all from dog-select
// And I select all from fish-select

// <g:ListBox label="Cat" debugId="cat-select" />
// <a:ComboBox label="Dog" id="dog-select" />
// <a:Select label="Fish" id="fish-select" />

