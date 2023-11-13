package net.kcww.app.robogen.translator.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.widget.GwtWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.VaadinWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.Widget;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class Rules {

    // Collects all widgets which are relevant to the given Selenium keyword
    public static List<Widget> collectRelevantWidgets(SeleniumKeyword seleniumKeyword) {
        return Stream.concat(Stream.of(GwtWidgetEnum.values()), Stream.of(VaadinWidgetEnum.values()))
                .filter(widget -> widget.aptKeywords().contains(seleniumKeyword))
                .collect(Collectors.toList());
    }

    // Selenium keyword elicit criteria (Decision tree/table?)
    // 1. Consider "arguments" in RelationModel
    //    1.1 locator: RelationModel.getXmlElement().getId() or RelationModel.getXmlElement().getName()
    //    1.2 if locator is present, then determine from RelationModel.getXmlElement().getTag()
    //
    //    1.3 other arguments: consider to extract them from RelationModel.getScenarioStep().getOriginalText()
    //            - temporalToken: like 1s, 5s, 10s, 1m, 5m, 10m, 1h, 5h, 10h, 1d, 5d, 10d
    // 2. Determine from certain tokens in RelationModel.getScenarioStep().getOriginalText() like ...
    //        - "visible", "invisible", "enabled", "disabled", "selected", "unselected", "checked", "unchecked"
    // 3. Determine from RelationModel.getScenarioStep().getKeywordType()

    // Can relationModel be translated to a KeywordModel based on the relevant widget and seleniumKeyword
//    public static boolean isApplicableForStep(List<StepKeywordType> stepKeywordTypes, RelationModel relationModel,
//                                              SeleniumKeyword seleniumKeyword, List<Widget> relevantWidgets) {
//        // Verify stepKeywordType
//        var stepKeywordType = relationModel.getScenarioStep().getStepKeywordType();
//        var isStepKeywordTypeCorrect = stepKeywordTypes.stream().anyMatch(type -> type == stepKeywordType);
//        // Does Scenario step involve an XML element?
//        var isXmlElementPresent = relationModel.getXmlElement() != null;
//        // Case when no Widgets are associated with the seleniumKeyword
//        if (relevantWidgets.isEmpty()) {
//            return isStepKeywordTypeCorrect && !isXmlElementPresent && areTokensCorrect(relationModel, seleniumKeyword);
//        }
//        // Case when Widgets are associated with the seleniumKeyword
//        return isStepKeywordTypeCorrect && isXmlElementPresent &&
//                relevantWidgets.stream().anyMatch(widget -> isWidgetRelevant(widget, relationModel, seleniumKeyword));
//    }

    // Is the given widget applicable based on the XML tag, included tokens, and excluded tokens
//    private static boolean isWidgetRelevant(Widget widget, RelationModel relationModel, SeleniumKeyword seleniumKeyword) {
//        var xmlTag = relationModel.getXmlElement().getTag();
//        return null != xmlTag && xmlTag.equalsIgnoreCase(widget.tag()) &&
//                areTokensCorrect(relationModel, seleniumKeyword);
//    }

    // Ensures that the tokens present in the stepText are correct
//    private static boolean areTokensCorrect(RelationModel relationModel, SeleniumKeyword seleniumKeyword) {
//        var stepText = relationModel.getScenarioStep().getText();
//        return areTokensPresentInStep(seleniumKeyword.properties().includedTokens(), stepText) &&
//                areTokensAbsentFromStep(seleniumKeyword.properties().excludedTokens(), stepText);
//    }

    // Ensures that at least one token from each set of included tokens is present in the text
//    private static boolean areTokensPresentInStep(List<Set<String>> includedTokens, String stepText) {
//        return includedTokens.stream().allMatch(tokens -> tokens.stream().anyMatch(token -> KeyMatcher.hasKey(token, stepText)));
//    }

    // Ensures that none of the tokens from any set of excluded tokens is present in the stepText
//    private static boolean areTokensAbsentFromStep(List<Set<String>> excludedTokens, String stepText) {
//        return excludedTokens.stream().noneMatch(tokens -> tokens.stream().anyMatch(token -> KeyMatcher.hasKey(token, stepText)));
//    }

    // Translates a RelationModel and ActionKeywordEnum to a KeywordModel
//    public static KeywordModel translateToKeyword(RelationModel relationModel, SeleniumKeyword seleniumKeyword) {
//        var builder = KeywordModel.builder().keyword(seleniumKeyword.keyword());
//
//        if (seleniumKeyword.properties().hasLocator()) {
//            var locator = Optional.ofNullable(relationModel.getXmlElement())
//                    .map(xmlElement -> null != xmlElement.getId() ? xmlElement.getId() : xmlElement.getName())
//                    // TODO: to revise and refactor
//                    .orElseThrow(() -> new IllegalStateException("No locator found for the relationModel."));
//            builder.locator(locator);
//
//            if (seleniumKeyword.properties().hasValue()) {
//                builder.value("${" + locator + "}");
//            }
//        }
//        return builder.build();
//    }
}
