package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static net.kcww.app.robogen.translator.helper.Tokens.*;
import static net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword.builder;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumElementVerificationKeywordEnum implements SeleniumKeyword {

    ELEMENT_SHOULD_BE_ENABLED("Element Should Be Enabled", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_BE_ENABLED_INCLUDED)
            .excludedTokens(ELEMENT_SHOULD_BE_ENABLED_EXCLUDED)
            .build()),
    ELEMENT_SHOULD_BE_DISABLED("Element Should Be Disabled", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_BE_DISABLED_INCLUDED)
            .build()),
    ELEMENT_SHOULD_BE_VISIBLE("Element Should Be Visible", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_BE_VISIBLE_INCLUDED)
            .excludedTokens(ELEMENT_SHOULD_BE_VISIBLE_EXCLUDED)
            .build()),
    ELEMENT_SHOULD_NOT_BE_VISIBLE("Element Should Not Be Visible", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_NOT_BE_VISIBLE_INCLUDED)
            .build()),

    CHECK_BOX_SHOULD_BE_SELECTED("Checkbox Should Be Selected", builder().hasLocator(true).build()),
    CHECK_BOX_SHOULD_NOT_BE_SELECTED("Checkbox Should Not Be Selected", builder().hasLocator(true)
            .includedTokens(UNCHECKED_INCLUDED)
            .build()),

    RADIO_BUTTON_SHOULD_BE_SET_TO("Radio Button Should Be Set To", builder().hasLocator(true).hasValue(true).build()),
    RADIO_BUTTON_SHOULD_NOT_BE_SELECTED("Radio Button Should Not Be Selected", builder().hasLocator(true).build()),

    LIST_SELECTION_SHOULD_BE("List Selection Should Be", builder().hasLocator(true).hasValue(true).build()),
    LIST_SHOULD_HAVE_NO_SELECTIONS("List Should Have No Selections", builder().hasLocator(true)
            .includedTokens(UNSELECT_ALL_FROM_LIST_INCLUDED)
            .build()),

    TEXTFIELD_SHOULD_CONTAIN("Textfield Should Contain", builder().hasLocator(true).hasValue(true).build()),
    TEXTAREA_SHOULD_CONTAIN("Textarea Should Contain", builder().hasLocator(true).hasValue(true).build());

    private final String keyword;
    private final SeleniumKeyword.Properties properties;
}
