package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class SelectFromListByLabelRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.SELECT_FROM_LIST_BY_LABEL;

    public SelectFromListByLabelRule() {
        super(SELENIUM_KEYWORD);
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return super.isApplicable(relation) && Words.hasSelection(relation.parsedStep().text());
    }
}

// Select From List By Label: [GWT_LIST_BOX, VAADIN_COMBO_BOX, VAADIN_SELECT]
// SELECT_FROM_LIST_BY_LABEL("Select From List By Label", ACTION, hasLoc()),    // locator, label(s)

// isApplicable: xmlElement().tagName(), Words.hasSelection()
// translate: keyword, {locator, label}

// And I select "Siamese" in cat-select
// And I select "Labrador" in dog-select
// And I select "Goldfish" in fish-select

// <g:ListBox label="Cat" debugId="cat-select" />
// <a:ComboBox label="Dog" id="dog-select" />
// <a:Select label="Fish" id="fish-select" />
