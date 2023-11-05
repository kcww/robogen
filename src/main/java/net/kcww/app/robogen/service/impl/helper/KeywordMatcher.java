package net.kcww.app.robogen.service.impl.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class KeywordMatcher {

  /**
   * Checks if the normalized form of the given keyword exists in the normalized form of the text,
   * possibly within angle brackets or double quotes.
   *
   * @param keyword the keyword to search for, which can include spaces
   * @param text    the text within which to search for the keyword
   * @return true if the normalized keyword is found in the normalized text, false otherwise
   */
  public static boolean containsKeyword(String keyword, String text) {
    String normalizedKeyword = normalize(keyword);
    String normalizedText = normalize(text);

    // Pattern to find normalized keyword possibly within angle brackets or double quotes
    String regex = "[<\"]" + Pattern.quote(normalizedKeyword) + "[>\"]";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(normalizedText);
//    log.info("regex: {}", regex);

    // Check for a match without any brackets or quotes
    boolean found = matcher.find();
    if (found) {
      return true;
    }

    // Check for a direct match in the text without normalizing spaces (for cases like 'first name')
    regex = "\\b" + Pattern.quote(keyword) + "\\b";
    pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    matcher = pattern.matcher(text);

    return matcher.find();
  }

  /**
   * Normalizes the string by removing spaces and converting to lowercase.
   *
   * @param input the string to normalize
   * @return a normalized string with no spaces and all lowercase
   */
  private static String normalize(String input) {
    return input.replaceAll("\\s+", "").toLowerCase();
  }
}
