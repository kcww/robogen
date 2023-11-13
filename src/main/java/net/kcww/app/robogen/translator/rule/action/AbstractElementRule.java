package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.widget.Widget;
import net.kcww.app.robogen.translator.rule.KeywordRule;

import java.util.List;

import static net.kcww.app.robogen.translator.helper.Rules.collectRelevantWidgets;

public abstract class AbstractElementRule implements KeywordRule<RelationModel, KeywordModel> {

    protected final SeleniumKeyword seleniumKeyword;
    protected final List<Widget> aptWidgets;

    protected AbstractElementRule(SeleniumKeyword seleniumKeyword) {
        this.seleniumKeyword = seleniumKeyword;
        this.aptWidgets = collectRelevantWidgets(seleniumKeyword);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (aptWidgets.isEmpty() ||
                relation.getXmlElement() == null ||
                relation.getScenarioStep().getId() == null) return false;
        return aptWidgets.stream().map(Widget::tag)
                .anyMatch(tag -> tag.equalsIgnoreCase(relation.getXmlElement().getTag()));
    }

    @Override
    public KeywordModel translate(RelationModel relationModel) {
        return null;
    }
}
