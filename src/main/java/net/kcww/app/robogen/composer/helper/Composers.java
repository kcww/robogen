package net.kcww.app.robogen.composer.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.translator.model.KeywordModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Composers {

  public static final String NEW_LINE = "\n";
  public static final String INDENT   = "    ";  // 4 spaces

  public static final String SELENIUM_LIBRARY = "SeleniumLibrary";
  public static final String DATA_DRIVER      = "DataDriver";
  public static final String START_WEB_TEST   = "Start Web Test";
  public static final String END_WEB_TEST     = "End Web Test";

  public static final String $_BROWSER = "${BROWSER}";
  public static final String $_URL     = "${URL}";
  public static final String $_DELAY   = "${DELAY}";
  public static final String $_TIMEOUT = "${TIMEOUT}";

  public static final String DEFAULT_BROWSER = "chrome";
  public static final String DEFAULT_URL     = "http://localhost:8080/";
  public static final String DEFAULT_DELAY   = "0.5";
  public static final String DEFAULT_TIMEOUT = "10";

  public static final String BLANK_PAGE = "about:blank";

  public static List<String> extractArguments(List<KeywordModel> keywords) {
    return keywords.stream()
            .map(KeywordModel::value)
            .filter(value -> value != null && !value.isBlank())
            .collect(Collectors.toList());
  }

  public static List<String> formatTestSteps(List<KeywordModel> keywords) {
    return keywords.stream().map(Composers::formatTestStep).collect(Collectors.toList());
  }

  private static String formatTestStep(KeywordModel keyword) {
    var stepBuilder = new StringBuilder(keyword.keyword());
    if (keyword.locator() != null) {
      stepBuilder.append(INDENT).append(keyword.locator());
    }
    if (keyword.value() != null) {
      stepBuilder.append(INDENT).append(keyword.value());
    }
    return stepBuilder.toString();
  }
}
