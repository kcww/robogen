package net.kcww.app.robogen.common.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public static boolean containsPattern(String statement, Set<Pattern> patterns) {
        return patterns.stream().anyMatch(pattern -> pattern.matcher(statement).find());
    }

    public static List<String> extractScenarioStepParameters(String text) {
        var normalizedText = normalize(text);
        return extractMatches(ANGLE_BRACKETS_PATTERN, normalizedText, 1);
    }

    public static Optional<String> extractScenarioStepId(String text, String token) {
        var normalizedText = normalize(text);
        var normalizedToken = normalize(token);
        var pattern = Pattern.compile("\\b" + Pattern.quote(normalizedToken) + "\\b", CASE_INSENSITIVE);
        return pattern.matcher(normalizedText).find() ? Optional.of(token) : Optional.empty();
    }

    public static List<String> extractScenarioStepLiterals(String text) {
        return extractMatches(DOUBLE_QUOTES_OR_CURLY_BRACKETS_PATTERN, text, 1);
    }

    public static boolean containsUrl(String text) {
        return URL_PATTERN.matcher(text).find();
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
