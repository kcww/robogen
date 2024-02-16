package net.kcww.app.robogen.translator.rule;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.translator.helper.Keywords;
import net.kcww.app.robogen.translator.helper.WidgetEnum;
import net.kcww.app.robogen.translator.helper.Widgets;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
public abstract class AbstractElementRule implements KeywordRule<StepRelation, Keyword> {

    private final SeleniumKeyword seleniumKeyword;
    private final EnumSet<WidgetEnum> relevantWidgets;

    protected AbstractElementRule(SeleniumKeyword seleniumKeyword) {
        this.seleniumKeyword = seleniumKeyword;
        this.relevantWidgets = Widgets.getRelevantWidgets(this.seleniumKeyword);
        validateWidgets(this.relevantWidgets);
    }

    private void validateWidgets(EnumSet<WidgetEnum> relevantWidgets) {
        if (relevantWidgets.isEmpty()) {
            String errorMessage = String.format("No relevant widgets found for keyword: %s", seleniumKeyword.keyword());
            log.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    @Override
    public boolean isApplicable(StepRelation relation) {
        return relation != null && relation.xmlElement() != null &&
                seleniumKeyword.isApplicable(relation.parsedStep().type()) &&
                relevantWidgets.stream().anyMatch(widget -> widget.tagName().equalsIgnoreCase(relation.xmlElement().tagName()));
    }

    @Override
    public Keyword translate(StepRelation relation) {
        return buildKeyword(relation, Keywords::determineArgument);
    }

    protected Keyword buildKeyword(StepRelation relation, Function<String, Optional<Keyword.Argument>> argFunc) {
        var builder = Keyword.builder().keyword(seleniumKeyword.keyword())
                .argument(Keywords.newArg(relation.xmlElement().id(), Keyword.Argument.Type.LOCATOR));

        if (seleniumKeyword.properties().hasArgument()) {
            argFunc.apply(relation.parsedStep().text()).ifPresent(builder::argument);
        }
        return builder.build();
    }
}
