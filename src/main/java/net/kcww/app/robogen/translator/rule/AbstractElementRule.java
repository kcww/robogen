package net.kcww.app.robogen.translator.rule;

import io.cucumber.messages.types.StepKeywordType;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.widget.GwtWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.VaadinWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.Widget;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractElementRule implements KeywordRule<RelationModel, KeywordModel> {

    protected final SeleniumKeyword seleniumKeyword;
    protected final List<Widget> aptWidgets;

    protected AbstractElementRule(SeleniumKeyword seleniumKeyword) {
        if (!seleniumKeyword.hasLocator())
            throw new IllegalArgumentException("Keyword " + seleniumKeyword.keyword() + " does not have locator");
        this.seleniumKeyword = seleniumKeyword;
        this.aptWidgets = collectRelevantWidgets(seleniumKeyword);
    }

    // Collects all relevant widgets for a given Selenium keyword
    private List<Widget> collectRelevantWidgets(SeleniumKeyword seleniumKeyword) {
        return Stream.concat(Stream.of(GwtWidgetEnum.values()), Stream.of(VaadinWidgetEnum.values()))
                .filter(widget -> widget.aptKeywords().contains(seleniumKeyword))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        var xmlElement = relation.xmlElement();
        if (aptWidgets.isEmpty() || xmlElement == null) return false;

        var scenarioStep = relation.scenarioStep();
        if (!matchesStepKeywordType(scenarioStep.type())) return false;

        return aptWidgets.stream()
                .map(Widget::tagName)
                .anyMatch(tag -> tag.equalsIgnoreCase(xmlElement.tagName()) &&
                        matchesTokenCondition(scenarioStep.text()));
    }

    protected abstract boolean matchesStepKeywordType(StepKeywordType type);

    protected abstract boolean matchesTokenCondition(String text);

    @Override
    public KeywordModel translate(RelationModel relation) throws IllegalArgumentException {
        var builder = KeywordModel.builder()
                .keyword(seleniumKeyword.keyword())
                .locator(relation.xmlElement().id());

        if (seleniumKeyword.hasArgument()) {
            var literals = relation.scenarioStep().literals();
            builder.value(!literals.isEmpty() ? literals.get(0) : "{" + relation.xmlElement().id() + "}");
        }
        return builder.build();
    }
}
