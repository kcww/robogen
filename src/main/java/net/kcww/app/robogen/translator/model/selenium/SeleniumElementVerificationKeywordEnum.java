package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumElementVerificationKeywordEnum implements SeleniumKeyword {

    ELEMENT_SHOULD_BE_ENABLED("Element Should Be Enabled", HAS_LOC, NO_ARG),
    ELEMENT_SHOULD_BE_DISABLED("Element Should Be Disabled", HAS_LOC, NO_ARG),

    ELEMENT_SHOULD_BE_VISIBLE("Element Should Be Visible", HAS_LOC, NO_ARG),
    ELEMENT_SHOULD_NOT_BE_VISIBLE("Element Should Not Be Visible", HAS_LOC, NO_ARG),

    CHECK_BOX_SHOULD_BE_SELECTED("Checkbox Should Be Selected", HAS_LOC, NO_ARG),
    CHECK_BOX_SHOULD_NOT_BE_SELECTED("Checkbox Should Not Be Selected", HAS_LOC, NO_ARG),

    RADIO_BUTTON_SHOULD_BE_SET_TO("Radio Button Should Be Set To", HAS_LOC, HAS_ARG),
    RADIO_BUTTON_SHOULD_NOT_BE_SELECTED("Radio Button Should Not Be Selected", HAS_LOC, NO_ARG),

    LIST_SELECTION_SHOULD_BE("List Selection Should Be", HAS_LOC, HAS_ARG),
    LIST_SHOULD_HAVE_NO_SELECTIONS("List Should Have No Selections", HAS_LOC, NO_ARG),

    TEXTFIELD_SHOULD_CONTAIN("Textfield Should Contain", HAS_LOC, HAS_ARG),
    TEXTAREA_SHOULD_CONTAIN("Textarea Should Contain", HAS_LOC, HAS_ARG);

    private final String keyword;
    private final boolean hasLocator;
    private final boolean hasArgument;
}
