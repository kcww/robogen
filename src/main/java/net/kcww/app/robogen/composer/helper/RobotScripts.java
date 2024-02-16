package net.kcww.app.robogen.composer.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.common.helper.Texts;
import net.kcww.app.robogen.parser.model.ParsedFeature;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.model.Keyword;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RobotScripts {

    public static final String NEW_LINE = "\n";
    public static final String INDENT = "    ";  // 4 spaces

    public static final String SELENIUM_LIBRARY = "SeleniumLibrary";
    public static final String DATA_DRIVER = "DataDriver";
    public static final String START_WEB_TEST = "Start Web Test";
    public static final String END_WEB_TEST = "End Web Test";

    public static final String $_BROWSER = "${BROWSER}";
    public static final String $_URL = "${URL}";
    public static final String $_DELAY = "${DELAY}";
    public static final String $_TIMEOUT = "${TIMEOUT}";

    public static final String DEFAULT_BROWSER = "chrome";
    public static final String DEFAULT_URL = "http://localhost:8080/";
    public static final String DEFAULT_DELAY = "0.5";
    public static final String DEFAULT_TIMEOUT = "10";

    public static final String BLANK_PAGE = "about:blank";

    public static String determineUrl(ParsedFeature parsedFeature, List<Keyword> keywords) {
        var singleLineFeatureDescription = parsedFeature.description().replaceAll("\\r?\\n", "");
        // First, try to find a URL in the feature description
        return Texts.findFirstUrl(singleLineFeatureDescription)
                .orElseGet(() -> keywords.stream()
                        // If not found in the feature description, search in the keywords
                        .filter(keyword -> {
                            var keywordName = keyword.keyword();
                            return SeleniumKeywordEnum.LOCATION_SHOULD_BE.keyword().equals(keywordName) ||
                                    SeleniumKeywordEnum.WAIT_UNTIL_LOCATION_IS.keyword().equals(keywordName);
                        })
                        .flatMap(keyword -> keyword.arguments().stream())
                        .filter(argument -> argument.type() == Keyword.Argument.Type.LITERAL)
                        .findFirst()
                        .map(Keyword.Argument::value)
                        // Use the default URL if no URL is found in the keywords
                        .orElse(DEFAULT_URL));
    }

    public static List<String> extractArguments(List<Keyword> keywords) {
        return keywords.stream()
                .flatMap(keyword -> keyword.arguments().stream())
                .filter(argument -> argument.type() == Keyword.Argument.Type.PARAMETER)
                .map(Keyword.Argument::toString)
                .collect(Collectors.toList());
    }

    public static List<String> formatTestSteps(List<Keyword> keywords) {
        return keywords.stream().map(RobotScripts::formatTestStep).collect(Collectors.toList());
    }

    private static String formatTestStep(Keyword keyword) {
        var stepBuilder = new StringBuilder(keyword.keyword());

        keyword.arguments().stream()
                .filter(argument -> argument.type() == Keyword.Argument.Type.LOCATOR)
                .findFirst()
                .ifPresent(argument -> stepBuilder.append(INDENT).append(argument.value()));

        keyword.arguments().stream()
                .filter(argument -> argument.type() == Keyword.Argument.Type.PARAMETER || argument.type() == Keyword.Argument.Type.LITERAL)
                .findFirst()
                .ifPresent(argument -> stepBuilder.append(INDENT).append(argument));

        return stepBuilder.toString();
    }
}
