package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumWaitingKeywordEnum implements SeleniumKeyword {

    WAIT_UNTIL_LOCATION_IS("Wait Until Location Is", NO_LOC, HAS_ARG),
    WAIT_UNTIL_LOCATION_IS_NOT("Wait Until Location Is Not", NO_LOC, HAS_ARG),

    WAIT_UNTIL_PAGE_CONTAINS("Wait Until Page Contains", NO_LOC, HAS_ARG),
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN("Wait Until Page Does Not Contain", NO_LOC, HAS_ARG),

    WAIT_UNTIL_PAGE_CONTAINS_ELEMENT("Wait Until Page Contains Element", HAS_LOC, HAS_ARG),
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN_ELEMENT("Wait Until Page Does Not Contain Element", HAS_LOC, HAS_ARG),

    WAIT_UNTIL_ELEMENT_CONTAINS("Wait Until Element Contains", HAS_LOC, HAS_ARG),
    WAIT_UNTIL_ELEMENT_DOES_NOT_CONTAIN("Wait Until Element Does Not Contain", HAS_LOC, HAS_ARG),

    WAIT_UNTIL_ELEMENT_IS_ENABLED("Wait Until Element Is Enabled", HAS_LOC, NO_ARG),

    WAIT_UNTIL_ELEMENT_IS_VISIBLE("Wait Until Element Is Visible", HAS_LOC, NO_ARG),
    WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE("Wait Until Element Is Not Visible", HAS_LOC, NO_ARG);

    private final String keyword;
    private final boolean hasLocator;
    private final boolean hasArgument;
}
