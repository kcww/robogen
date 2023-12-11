package net.kcww.app.robogen.common.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class TextMatcher {

    private static final Pattern ANGLE_BRACKETS_PATTERN = Pattern.compile(
            "<([^>]+)>", CASE_INSENSITIVE | MULTILINE);

    private static final Pattern DOUBLE_QUOTES_OR_CURLY_BRACKETS_PATTERN = Pattern.compile(
            "\"([^\"]+)\"|\\{([^}]+)}", CASE_INSENSITIVE);

    private static final Pattern URL_PATTERN = Pattern.compile(
            "\\b(http(s?)://|www\\.)([-a-zA-Z0-9@:%_+.~#?&/=]*)",
            CASE_INSENSITIVE | MULTILINE | DOTALL);

    public static List<String> extractScenarioStepParameters(String text) {
        var normalizedText = normalize(text);
        return extractMatches(ANGLE_BRACKETS_PATTERN, normalizedText, 1);
    }

    public static Optional<String> extractScenarioStepId(String text, String xmlElementId) {
        var normalizedId = normalize(xmlElementId);
        var pattern = Pattern.compile("\\b" + Pattern.quote(normalizedId) + "\\b", CASE_INSENSITIVE);
        return pattern.matcher(text).find() ? Optional.of(normalizedId) : Optional.empty();
    }

    public static List<String> extractScenarioStepLiterals(String text) {
        return extractMatches(DOUBLE_QUOTES_OR_CURLY_BRACKETS_PATTERN, text, 1);
    }

    public static List<String> extractUrls(String text) {
        return extractMatches(URL_PATTERN, text, 0);
    }

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

    public static String normalize(String text) {
        return text.replaceAll("\\s+", "").toLowerCase();
    }
}
