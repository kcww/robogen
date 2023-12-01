package net.kcww.app.robogen.translator.helper;

import net.kcww.app.robogen.common.helper.TextMatcher;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface Tokens {

    String ROBOT_EMPTY = "${EMPTY}";

    Set<String> BE_TOKENS = Set.of("be", "is", "are", "been", "being");
    Set<String> NEGATIVE_TOKENS = Set.of("not", "don't", "doesn't", "isn't", "aren't", "no", "none", "nothing", "never");

    Set<String> ALL_TOKENS = Set.of("all", "every", "each", "whole", "entire");

    Set<String> SELECT_TOKENS = Set.of("check", "select", "set", "choose", "chose", "chosen", "enter", "put", "mark");
    Set<String> UNSELECT_TOKENS = Set.of("uncheck", "deselect", "unselect", "unset", "unchoose", "unchose", "unchosen", "unmark");

    Set<String> ENABLED_TOKENS = Set.of("enable", "enabled");
    Set<String> DISABLED_TOKENS = Set.of("disable", "disabled");

    Set<String> VISIBLE_TOKENS = Set.of("visible", "shown", "displayed", "seen", "appeared", "present");
    Set<String> INVISIBLE_TOKENS = Set.of("invisible", "unseen", "disappeared", "absent", "hidden");

    Set<String> PAGE_TOKENS = Set.of("page", "screen", "display");
    Set<String> TITLE_TOKENS = Set.of("title");
    Set<String> WAIT_TOKENS = Set.of("wait", "delay");

    Set<Pattern> BE_PATTERNS = createPatterns(BE_TOKENS);
    Set<Pattern> NEGATIVE_PATTERNS = createPatterns(NEGATIVE_TOKENS);
    Set<Pattern> ALL_PATTERNS = createPatterns(ALL_TOKENS);
    Set<Pattern> SELECT_PATTERNS = createPatterns(SELECT_TOKENS);
    Set<Pattern> UNSELECT_PATTERNS = createPatterns(UNSELECT_TOKENS);
    Set<Pattern> ENABLED_PATTERNS = createPatterns(ENABLED_TOKENS);
    Set<Pattern> DISABLED_PATTERNS = createPatterns(DISABLED_TOKENS);
    Set<Pattern> VISIBLE_PATTERNS = createPatterns(VISIBLE_TOKENS);
    Set<Pattern> INVISIBLE_PATTERNS = createPatterns(INVISIBLE_TOKENS);
    Set<Pattern> PAGE_PATTERNS = createPatterns(PAGE_TOKENS);
    Set<Pattern> TITLE_PATTERNS = createPatterns(TITLE_TOKENS);
    Set<Pattern> WAIT_PATTERNS = createPatterns(WAIT_TOKENS);

    private static Set<Pattern> createPatterns(Set<String> tokens) {
        return tokens.stream()
                .map(token -> {
                    String basePattern = Pattern.quote(token);
                    String suffixPattern;
                    if (basePattern.endsWith("y")) { // Replace the final 'y' with '(ied|ies|y)' to match 'y', 'ied', and 'ies'
                        basePattern = basePattern.substring(0, basePattern.length() - 1) + "(ied|ies|y)";
                        suffixPattern = "(s|es)?";
                    } else {
                        suffixPattern = "(ed|s|es)?";
                    }
                    return Pattern.compile("\\b" + basePattern + suffixPattern + "\\b", Pattern.CASE_INSENSITIVE);
                })
                .collect(Collectors.toSet());
    }

    static boolean isSelectAction(String text) {
        return hasPatterns(text, SELECT_PATTERNS, UNSELECT_PATTERNS);
    }

    static boolean isUnselectAction(String text) {
        return hasPatterns(text, UNSELECT_PATTERNS, SELECT_PATTERNS);
    }

    static boolean isSelectAllAction(String text) {
        return hasPatterns(text, ALL_PATTERNS) && isSelectAction(text);
    }

    static boolean isUnselectAllAction(String text) {
        return hasPatterns(text, ALL_PATTERNS) && isUnselectAction(text);
    }

    static boolean isEnableState(String text) {
        return hasPatterns(text, ENABLED_PATTERNS, DISABLED_PATTERNS);
    }

    static boolean isDisableState(String text) {
        return hasPatterns(text, DISABLED_PATTERNS, ENABLED_PATTERNS);
    }

    static boolean isVisibleState(String text) {
        return hasPatterns(text, VISIBLE_PATTERNS, INVISIBLE_PATTERNS);
    }

    static boolean isInvisibleState(String text) {
        return hasPatterns(text, INVISIBLE_PATTERNS, VISIBLE_PATTERNS);
    }

    static boolean isWaitingEnableState(String text) {
        return isWaitingState(text) && isEnableState(text);
    }

    static boolean isWaitingVisibleState(String text) {
        return isWaitingState(text) && isVisibleState(text);
    }

    static boolean isWaitingInvisibleState(String text) {
        return isWaitingState(text) && isInvisibleState(text);
    }

    static boolean isWaitingState(String text) {
        return TextMatcher.containsPattern(text, Tokens.WAIT_PATTERNS);
    }

    private static boolean hasPatterns(String statement, Set<Pattern> patterns) {
        return TextMatcher.containsPattern(statement, patterns);
    }

    // positive patterns for positive declarative statements
    // negative patterns for negative declarative statements
    private static boolean hasPatterns(String text, Set<Pattern> positivePatterns, Set<Pattern> negativePatterns) {
        var hasNegative = hasPatterns(text, NEGATIVE_PATTERNS);
        return (!hasNegative && hasPatterns(text, positivePatterns)) ||
                (hasNegative && hasPatterns(text, negativePatterns));
    }
}
