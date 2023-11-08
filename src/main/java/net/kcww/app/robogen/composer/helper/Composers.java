package net.kcww.app.robogen.composer.helper;

import net.kcww.app.robogen.translator.model.KeywordModel;

import java.util.List;
import java.util.stream.Collectors;

public class Composers {

  public static final String NEW_LINE = "\n";
  public static final String INDENT = "    ";  // 4 spaces

  public static final String START_WEB_TEST = "Start Web Test";
  public static final String END_WEB_TEST = "End Web Test";
  public static final String DEFAULT_BROWSER = "chrome";
  public static final String DEFAULT_URL = "http://localhost:8080/";
  public static final String DEFAULT_DELAY = "0.5";
  public static final String DEFAULT_TIMEOUT = "10";
  public static final String DEFAULT_RETRY = "3";

  public static List<String> extractArguments(List<KeywordModel> keywords) {
    return keywords.stream().map(KeywordModel::getArgument).collect(Collectors.toList());
  }

  public static List<String> formatTestSteps(List<KeywordModel> keywords) {
    return keywords.stream().map(Composers::formatTestStep).collect(Collectors.toList());
  }

  private static String formatTestStep(KeywordModel keyword) {
    var stepBuilder = new StringBuilder(keyword.getKeyword());
    if (keyword.getLocator() != null) {
      stepBuilder.append(INDENT).append(keyword.getLocator());
    }
    if (keyword.getArgument() != null) {
      stepBuilder.append(INDENT).append(keyword.getArgument());
    }
    return stepBuilder.toString();
  }
}
