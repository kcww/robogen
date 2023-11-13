package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static net.kcww.app.robogen.translator.helper.Tokens.*;
import static net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword.builder;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumElementActionKeywordEnum implements SeleniumKeyword {

    CHOOSE_FILE("Choose File", builder().hasLocator(true).hasValue(true).build()),
    // TODO: StepText includes "click" or "clicks" token, has <locator>, but Widget.tag cannot be found.
    CLICK_ELEMENT("Click Element", builder().hasLocator(true).build()),
    CLICK_BUTTON("Click Button", builder().hasLocator(true).build()),
    CLICK_IMAGE("Click Image", builder().hasLocator(true).build()),
    CLICK_LINK("Click Link", builder().hasLocator(true).build()),

    INPUT_TEXT("Input Text", builder().hasLocator(true).hasValue(true).build()),
    INPUT_PASSWORD("Input Password", builder().hasLocator(true).hasValue(true).build()),
    // TODO: special case? -- like 'reset' or 'clear' button.
    CLEAR_ELEMENT_TEXT("Clear Element Text", builder().hasLocator(true).build()),
    // TODO: special case? -- user input 'enter' key on the last input element to submit the form.
    PRESS_KEYS("Press Keys", builder().hasLocator(true).hasValue(true).build()),

    SELECT_ALL_FROM_LIST("Select All From List", builder().hasLocator(true)
            .includedTokens(SELECT_ALL_FROM_LIST_INCLUDED)
            .excludedTokens(SELECT_ALL_FROM_LIST_EXCLUDED).build()),
    UNSELECT_ALL_FROM_LIST("Unselect All From List", builder().hasLocator(true)
            .includedTokens(UNSELECT_ALL_FROM_LIST_INCLUDED).build()),

    SELECT_FROM_LIST_BY_LABEL("Select From List By Label", builder().hasLocator(true).hasValue(true)
            .excludedTokens(SELECT_FROM_LIST_EXCLUDED).build()),
    UNSELECT_FROM_LIST_BY_LABEL("Unselect From List By Label", builder().hasLocator(true).hasValue(true)
            .includedTokens(UNSELECT_FROM_LIST_INCLUDED).build()),

    SELECT_CHECKBOX("Select Checkbox", builder().hasLocator(true)
            .excludedTokens(CHECKED_EXCLUDED).build()),
    UNSELECT_CHECKBOX("Unselect Checkbox", builder().hasLocator(true)
            .includedTokens(UNCHECKED_INCLUDED).build()),

    SELECT_RADIO_BUTTON("Select Radio Button", builder().hasLocator(true).hasValue(true).build());

    private final String keyword;
    private final SeleniumKeyword.Properties properties;
}
