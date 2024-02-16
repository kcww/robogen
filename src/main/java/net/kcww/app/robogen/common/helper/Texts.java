package net.kcww.app.robogen.common.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class Texts {

    private static final Pattern ANGLE_BRACKETS_PATTERN = Pattern.compile("<([^>]+)>", CASE_INSENSITIVE);
    private static final Pattern DOUBLE_QUOTES_PATTERN = Pattern.compile("\"([^\"]+)\"", CASE_INSENSITIVE);
    private static final Pattern CURLY_BRACKETS_PATTERN = Pattern.compile("\\{([^}]+)}", CASE_INSENSITIVE);
    private static final Pattern URL_PATTERN = Pattern.compile(
            "\\b(http(s?)://|www\\.)((localhost)|([-a-zA-Z0-9@:%_+.~#?&/=]*))(:(\\d+))?([-a-zA-Z0-9@:%_+.~#?&=/]*)",
            CASE_INSENSITIVE);

    public static boolean containsWord(String text, String word) {
        if (text == null || word == null) return false;
        Matcher matcher = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", CASE_INSENSITIVE).matcher(text);
        return matcher.find();
    }

    public static Optional<String> findFirstStepParameter(String text) {
        return findFirstMatch(ANGLE_BRACKETS_PATTERN, normalize(text));
    }

    public static Optional<String> findFirstStepLiteral(String text) {
        Optional<String> firstDoubleQuotesLiteral = findFirstMatch(DOUBLE_QUOTES_PATTERN, text);
        return firstDoubleQuotesLiteral.isPresent() ? firstDoubleQuotesLiteral : findFirstMatch(CURLY_BRACKETS_PATTERN, text);
    }

    private static Optional<String> findFirstMatch(Pattern pattern, String text) {
        if (text == null) return Optional.empty();
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? Optional.of(matcher.group(1)) : Optional.empty();
    }

    public static boolean containsUrl(String text) {
        if (text == null) return false;
        return URL_PATTERN.matcher(text).find();
    }

    public static Optional<String> findFirstUrl(String text) {
        if (text == null) return Optional.empty();

        Matcher matcher = URL_PATTERN.matcher(text);
        return matcher.find() ? Optional.of(matcher.group().replaceAll("[,.']$", "")) : Optional.empty();
    }

    private static String normalize(String text) {
        return text == null ? "" : text.replaceAll("\\s+", "").toLowerCase();
    }

    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;

        return Arrays.stream(text.split("\\s+"))
                .map(word -> word.isEmpty() ? word :
                        Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
