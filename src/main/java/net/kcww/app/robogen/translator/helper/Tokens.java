package net.kcww.app.robogen.translator.helper;

import java.util.List;
import java.util.Set;

/**
 * Defines constants representing various action and verification tokens
 * used in Selenium automated testing. This interface categorizes tokens
 * based on their usage in different Selenium elements and actions.
 * Note: Scenarios involving "double negatives" are not supported for simplicity
 * and to avoid ambiguity in token interpretation.
 */
public interface Tokens {

    // Used for LIST elements in single selection contexts
    Set<String> NEGATIVE_TOKENS = Set.of("not", "n't", "no", "none", "nothing", "never");
    List<List<Set<String>>> SELECT_FROM_LIST_EXCLUDED = List.of(List.of(NEGATIVE_TOKENS));

    Set<String> UNSELECT_TOKENS = Set.of("uncheck", "deselect", "unselect", "unset", "none");
    List<List<Set<String>>> UNSELECT_FROM_LIST_INCLUDED = List.of(List.of(UNSELECT_TOKENS));

    // Used for LIST elements in multi-selection contexts
    Set<String> ALL_TOKENS = Set.of("all", "every", "each", "whole", "entire");
    List<List<Set<String>>> SELECT_ALL_FROM_LIST_INCLUDED = List.of(List.of(ALL_TOKENS));
    List<List<Set<String>>> SELECT_ALL_FROM_LIST_EXCLUDED = List.of(List.of(NEGATIVE_TOKENS));

    List<List<Set<String>>> UNSELECT_ALL_FROM_LIST_INCLUDED = List.of(List.of(ALL_TOKENS, UNSELECT_TOKENS));

    // Tokens for CHECKBOX elements
    List<List<Set<String>>> CHECKED_EXCLUDED = List.of(List.of(NEGATIVE_TOKENS));
    List<List<Set<String>>> UNCHECKED_INCLUDED = List.of(List.of(NEGATIVE_TOKENS, UNSELECT_TOKENS));

    // Tokens related to PAGE verification
    List<List<Set<String>>> PAGE_SHOULD_CONTAIN_INCLUDED = List.of(List.of(Set.of("page", "screen", "display")));
    List<List<Set<String>>> PAGE_SHOULD_CONTAIN_EXCLUDED = List.of(List.of(NEGATIVE_TOKENS));

    // Tokens for ENABLED elements
    Set<String> ENABLED_TOKENS = Set.of("enable", "enabled");
    List<List<Set<String>>> ELEMENT_SHOULD_BE_ENABLED_INCLUDED = List.of(List.of(ENABLED_TOKENS));
    List<List<Set<String>>> ELEMENT_SHOULD_BE_ENABLED_EXCLUDED = List.of(List.of(NEGATIVE_TOKENS));

    Set<String> DISABLED_TOKENS = Set.of("disable", "disabled");
    List<List<Set<String>>> ELEMENT_SHOULD_BE_DISABLED_INCLUDED = List.of(
            List.of(DISABLED_TOKENS),
            List.of(NEGATIVE_TOKENS, ENABLED_TOKENS));

    // Tokens for visibility of elements
    Set<String> VISIBLE_TOKENS = Set.of("visible", "shown", "displayed", "seen", "appeared", "present");
    List<List<Set<String>>> ELEMENT_SHOULD_BE_VISIBLE_INCLUDED = List.of(List.of(VISIBLE_TOKENS));
    List<List<Set<String>>> ELEMENT_SHOULD_BE_VISIBLE_EXCLUDED = List.of(List.of(NEGATIVE_TOKENS));

    Set<String> INVISIBLE_TOKENS = Set.of("invisible", "unseen", "disappeared", "absent", "hidden");
    List<List<Set<String>>> ELEMENT_SHOULD_NOT_BE_VISIBLE_INCLUDED = List.of(
            List.of(INVISIBLE_TOKENS),
            List.of(NEGATIVE_TOKENS, VISIBLE_TOKENS));
}
