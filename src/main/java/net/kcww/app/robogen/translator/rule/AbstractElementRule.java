package net.kcww.app.robogen.translator.rule;

import io.cucumber.messages.types.StepKeywordType;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Widgets;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.widget.Widget;
import net.kcww.app.robogen.translator.model.widget.WidgetEnum;

import java.util.EnumSet;

public abstract class AbstractElementRule implements KeywordRule<RelationModel, KeywordModel> {

    private final SeleniumKeyword seleniumKeyword;
    private final EnumSet<WidgetEnum> relevantWidgets;

    protected AbstractElementRule(SeleniumKeyword seleniumKeyword) {
        this.seleniumKeyword = seleniumKeyword;
        this.relevantWidgets = Widgets.getRelevantWidgets(this.seleniumKeyword);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        // assert !relevantWidgets.isEmpty() : "Relevant widgets must not be empty.";
        if (relation.xmlElement() == null) return false;

        if (!(isAction(relation) || isVerification(relation))) return false;

        String widgetTag = relation.xmlElement().tagName();
        return relevantWidgets.stream()
                .map(Widget::tagName)
                .anyMatch(tag -> tag.equalsIgnoreCase(widgetTag));
    }

    private boolean isAction(RelationModel relation) {
        return seleniumKeyword.properties().type() == SeleniumKeyword.Type.ACTION &&
                relation.scenarioStep().type() == StepKeywordType.ACTION;
    }

    private boolean isVerification(RelationModel relation) {
        var keywordType = seleniumKeyword.properties().type();
        var stepType = relation.scenarioStep().type();
        return keywordType == SeleniumKeyword.Type.VERIFICATION &&
                (stepType == StepKeywordType.CONTEXT || stepType == StepKeywordType.OUTCOME);
    }

    @Override
    public KeywordModel translate(RelationModel relation) throws IllegalArgumentException {
        var builder = KeywordModel.builder()
                .keyword(seleniumKeyword.keyword())
                .locator(relation.xmlElement().id());

        if (seleniumKeyword.properties().hasArgument()) {
            String value = relation.scenarioStep().literals().stream().findFirst().orElse("{" + relation.xmlElement().id() + "}");
            builder.value(value);
        }
        return builder.build();
    }
}
