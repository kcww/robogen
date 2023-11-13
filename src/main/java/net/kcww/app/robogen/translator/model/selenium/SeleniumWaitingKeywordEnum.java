package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static net.kcww.app.robogen.translator.helper.Tokens.*;
import static net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword.builder;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumWaitingKeywordEnum implements SeleniumKeyword {

    WAIT_UNTIL_LOCATION_IS("Wait Until Location Is", builder().hasValue(true).build()),
    WAIT_UNTIL_LOCATION_IS_NOT("Wait Until Location Is Not", builder().hasValue(true).build()),

    WAIT_UNTIL_PAGE_CONTAINS("Wait Until Page Contains", builder().hasValue(true).build()),
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN("Wait Until Page Does Not Contain", builder().hasValue(true).build()),

    WAIT_UNTIL_PAGE_CONTAINS_ELEMENT("Wait Until Page Contains Element", builder().hasLocator(true).hasValue(true).build()),
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN_ELEMENT("Wait Until Page Does Not Contain Element", builder().hasLocator(true).hasValue(true).build()),

    WAIT_UNTIL_ELEMENT_CONTAINS("Wait Until Element Contains", builder().hasLocator(true).hasValue(true).build()),
    WAIT_UNTIL_ELEMENT_DOES_NOT_CONTAIN("Wait Until Element Does Not Contain", builder().hasLocator(true).hasValue(true).build()),

    WAIT_UNTIL_ELEMENT_IS_ENABLED("Wait Until Element Is Enabled", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_BE_ENABLED_INCLUDED)
            .excludedTokens(ELEMENT_SHOULD_BE_ENABLED_EXCLUDED)
            .build()),

    WAIT_UNTIL_ELEMENT_IS_VISIBLE("Wait Until Element Is Visible", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_BE_VISIBLE_INCLUDED)
            .excludedTokens(ELEMENT_SHOULD_BE_VISIBLE_EXCLUDED)
            .build()),
    WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE("Wait Until Element Is Not Visible", builder().hasLocator(true)
            .includedTokens(ELEMENT_SHOULD_NOT_BE_VISIBLE_INCLUDED)
            .build());

    private final String keyword;
    private final SeleniumKeyword.Properties properties;
}
