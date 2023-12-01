package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumElementActionKeywordEnum implements SeleniumKeyword {

    CHOOSE_FILE("Choose File", HAS_LOC, HAS_ARG),
    // TODO: StepText includes "click" or "clicks" token, has <locator>, but Widget.tag cannot be found.
    CLICK_ELEMENT("Click Element", HAS_LOC, NO_ARG),
    CLICK_BUTTON("Click Button", HAS_LOC, NO_ARG),
    CLICK_IMAGE("Click Image", HAS_LOC, NO_ARG),
    CLICK_LINK("Click Link", HAS_LOC, NO_ARG),

    INPUT_TEXT("Input Text", HAS_LOC, HAS_ARG),
    INPUT_PASSWORD("Input Password", HAS_LOC, HAS_ARG),
    // TODO: special case? -- like 'reset' or 'clear' button.
    CLEAR_ELEMENT_TEXT("Clear Element Text", HAS_LOC, NO_ARG),
    // TODO: special case? -- user input 'enter' key on the last input element to submit the form.
    PRESS_KEYS("Press Keys", true, HAS_LOC),

    SELECT_ALL_FROM_LIST("Select All From List", HAS_LOC, NO_ARG),
    UNSELECT_ALL_FROM_LIST("Unselect All From List", HAS_LOC, NO_ARG),

    SELECT_FROM_LIST_BY_LABEL("Select From List By Label", HAS_LOC, HAS_ARG),
    UNSELECT_FROM_LIST_BY_LABEL("Unselect From List By Label", HAS_LOC, HAS_ARG),

    SELECT_CHECKBOX("Select Checkbox", HAS_LOC, NO_ARG),
    UNSELECT_CHECKBOX("Unselect Checkbox", HAS_LOC, NO_ARG),

    SELECT_RADIO_BUTTON("Select Radio Button", HAS_LOC, HAS_ARG);

    private final String keyword;
    private final boolean hasLocator;
    private final boolean hasArgument;
}
