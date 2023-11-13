package net.kcww.app.robogen.common.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for matching and extracting specific patterns such as URLs and keys from text.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class KeyMatcher {

    /**
     * Pattern for finding and capturing text within angle brackets, parentheses, square brackets, single quotes,
     * and double quotes, across multiple lines and in a case-insensitive manner.
     */
    private static final Pattern XML_ELEMENT_ID_IN_SCENARIO_STEP_TEXT_PATTERNS = Pattern.compile(
            "<([^>]+)>|\\(([^)]+)\\)|\\[([^\\]]+)\\]|'([^']+)'|\"([^\"]+)\"",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);


    private static final Pattern XML_ELEMENT_ID_IN_SCENARIO_STEP_PATTERN = Pattern.compile(
            "<([^>]+)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

//    private static final Pattern VALUE_IN_SCENARIO_STEP_PATTERN = Pattern.compile(
//            "\\{([^}]+)\\}", Pattern.CASE_INSENSITIVE);

    /**
     * Pattern for recognizing URLs with http or https, based off RFC 3986.
     */
    private static final Pattern URL_PATTERN = Pattern.compile(
            "(?:^|[\\W])(http(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    private static final String DELIMITED_KEYWORD_PATTERN_TEMPLATE = "\\<%1$s\\>|\\(%1$s\\)|\\[%1$s\\]|'%1$s'|\"%1$s\"";

    /**
     * Checks if the given keyword exists in the text, first looking for the keyword within specified
     * delimiters such as angle brackets or double quotes, and if not found, then checking for the keyword
     * without delimiters as a standalone word.
     *
     * @param keyword the keyword to search for, which can include spaces
     * @param text    the text within which to search for the keyword
     * @return true if the keyword is found in the text either within delimiters or as a standalone word, false otherwise
     */
    public static boolean hasKey(String keyword, String text) {
        String normalizedKeyword = normalize(keyword);
        String normalizedText = normalize(text);

        // Check for the keyword within delimiters first in the normalized text
        String regex = String.format(DELIMITED_KEYWORD_PATTERN_TEMPLATE, Pattern.quote(normalizedKeyword));
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(normalizedText);
        if (matcher.find()) return true;

        // If not found, check for the keyword as a standalone word in the normalized text
        regex = "\\b" + Pattern.quote(normalizedKeyword) + "\\b";
        matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(normalizedText);
        return matcher.find();
    }

    public static String extractXmlElementIdFromScenarioStepText(String text) {
        return extractMatches(XML_ELEMENT_ID_IN_SCENARIO_STEP_PATTERN, text, 0).stream()
                .findFirst().orElse("");
    }

//    public static String extractValueFromScenarioStepText(String text) {
//        return extractMatches(VALUE_IN_SCENARIO_STEP_PATTERN, text, 0).stream()
//                .findFirst().orElse("");
//    }

    /**
     * Checks if the text contains any valid URLs.
     *
     * @param text the text to be checked for URLs
     * @return true if at least one URL is found, false otherwise
     */
    public static boolean containsUrl(String text) {
        return URL_PATTERN.matcher(text).find();
    }

    /**
     * Extracts URLs from the provided text.
     *
     * @param text the text from which to extract URLs
     * @return a list of extracted URLs
     */
    public static List<String> extractUrls(String text) {
        return extractMatches(URL_PATTERN, text, 0);
    }

    /**
     * Extracts matches from the provided text based on the specified pattern.
     * If a group is specified, only the text from that group is extracted.
     * Group 0 means the entire pattern, other group numbers correspond to specific capturing groups in the pattern.
     *
     * @param pattern the pattern to match against
     * @param text    the text to be searched
     * @param group   the group number to extract (0 for entire match, 1 for first capturing group, etc.)
     * @return a list of extracted matches
     */
    private static List<String> extractMatches(Pattern pattern, String text, int group) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String match = matcher.group(group);
            if (match != null && !match.isEmpty()) {
                // If group is 1 and URL pattern is used, clean up the URL.
                if (group == 1 && pattern == URL_PATTERN) {
                    match = match.replaceAll("[,.']$", "");
                }
                matches.add(match);
            }
        }
        return matches;
    }

    /**
     * Normalizes a string by removing all spaces and converting it to lowercase.
     *
     * @param input the string to normalize
     * @return the normalized string
     */
    public static String normalize(String input) {
        return input.replaceAll("\\s+", "").toLowerCase();
    }
}
