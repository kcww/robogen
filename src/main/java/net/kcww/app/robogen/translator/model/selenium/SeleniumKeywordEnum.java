package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword.Type.*;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumKeywordEnum implements SeleniumKeyword {

    // Element Action Keywords
    CHOOSE_FILE("Choose File", type(ACTION).hasLocator(true).hasArgument(true).build()),
    CLICK_ELEMENT("Click Element", type(ACTION).hasLocator(true).build()),
    CLICK_BUTTON("Click Button", type(ACTION).hasLocator(true).build()),
    CLICK_IMAGE("Click Image", type(ACTION).hasLocator(true).build()),
    CLICK_LINK("Click Link", type(ACTION).hasLocator(true).build()),
    INPUT_TEXT("Input Text",type(ACTION).hasLocator(true).hasArgument(true).build()),
    INPUT_PASSWORD("Input Password", type(ACTION).hasLocator(true).hasArgument(true).build()),
    // TODO: special case? -- like 'reset' or 'clear' button.
    CLEAR_ELEMENT_TEXT("Clear Element Text", type(ACTION).hasLocator(true).build()),
    // TODO: special case? -- user input 'enter' key on the last input element to submit the form.
    PRESS_KEYS("Press Keys", type(ACTION).hasLocator(true).hasArgument(true).build()),
    SELECT_ALL_FROM_LIST("Select All From List", type(ACTION).hasLocator(true).build()),
    UNSELECT_ALL_FROM_LIST("Unselect All From List", type(ACTION).hasLocator(true).build()),
    SELECT_FROM_LIST_BY_LABEL("Select From List By Label", type(ACTION).hasLocator(true).hasArgument(true).build()),
    UNSELECT_FROM_LIST_BY_LABEL("Unselect From List By Label", type(ACTION).hasLocator(true).hasArgument(true).build()),
    SELECT_CHECKBOX("Select Checkbox", type(ACTION).hasLocator(true).build()),
    UNSELECT_CHECKBOX("Unselect Checkbox", type(ACTION).hasLocator(true).build()),
    SELECT_RADIO_BUTTON("Select Radio Button", type(ACTION).hasLocator(true).hasArgument(true).build()),

    // Element Verification Keywords
    ELEMENT_SHOULD_BE_ENABLED("Element Should Be Enabled", type(VERIFICATION).hasLocator(true).build()),
    ELEMENT_SHOULD_BE_DISABLED("Element Should Be Disabled", type(VERIFICATION).hasLocator(true).build()),
    ELEMENT_SHOULD_BE_VISIBLE("Element Should Be Visible", type(VERIFICATION).hasLocator(true).build()),
    ELEMENT_SHOULD_NOT_BE_VISIBLE("Element Should Not Be Visible", type(VERIFICATION).hasLocator(true).build()),
    CHECK_BOX_SHOULD_BE_SELECTED("Checkbox Should Be Selected", type(VERIFICATION).hasLocator(true).build()),
    CHECK_BOX_SHOULD_NOT_BE_SELECTED("Checkbox Should Not Be Selected", type(VERIFICATION).hasLocator(true).build()),
    RADIO_BUTTON_SHOULD_BE_SET_TO("Radio Button Should Be Set To", type(VERIFICATION).hasLocator(true).hasArgument(true).build()),
    RADIO_BUTTON_SHOULD_NOT_BE_SELECTED("Radio Button Should Not Be Selected", type(VERIFICATION).hasLocator(true).build()),
    LIST_SELECTION_SHOULD_BE("List Selection Should Be", type(VERIFICATION).hasLocator(true).hasArgument(true).build()),
    LIST_SHOULD_HAVE_NO_SELECTIONS("List Should Have No Selections", type(VERIFICATION).hasLocator(true).build()),
    TEXTFIELD_SHOULD_CONTAIN("Textfield Should Contain", type(VERIFICATION).hasLocator(true).hasArgument(true).build()),
    TEXTAREA_SHOULD_CONTAIN("Textarea Should Contain", type(VERIFICATION).hasLocator(true).hasArgument(true).build()),

    // Waiting Keywords
    WAIT_UNTIL_ELEMENT_CONTAINS("Wait Until Element Contains", type(VERIFICATION).hasLocator(true).hasArgument(true).build()),
    WAIT_UNTIL_ELEMENT_DOES_NOT_CONTAIN("Wait Until Element Does Not Contain", type(VERIFICATION).hasLocator(true).hasArgument(true).build()),
    WAIT_UNTIL_ELEMENT_IS_ENABLED("Wait Until Element Is Enabled", type(VERIFICATION).hasLocator(true).build()),
    WAIT_UNTIL_ELEMENT_IS_VISIBLE("Wait Until Element Is Visible", type(VERIFICATION).hasLocator(true).build()),
    WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE("Wait Until Element Is Not Visible", type(VERIFICATION).hasLocator(true).build()),
    WAIT_UNTIL_LOCATION_IS("Wait Until Location Is", type(VERIFICATION).hasArgument(true).build()),
    WAIT_UNTIL_LOCATION_IS_NOT("Wait Until Location Is Not", type(VERIFICATION).hasArgument(true).build()),
    WAIT_UNTIL_PAGE_CONTAINS("Wait Until Page Contains", type(VERIFICATION).hasArgument(true).build()),
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN("Wait Until Page Does Not Contain", type(VERIFICATION).hasArgument(true).build()),
    WAIT_UNTIL_PAGE_CONTAINS_ELEMENT("Wait Until Page Contains Element", type(VERIFICATION).hasLocator(true).build()),
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN_ELEMENT("Wait Until Page Does Not Contain Element", type(VERIFICATION).hasLocator(true).build()),

    // Page Verification Keywords
    TITLE_SHOULD_BE("Title Should Be", type(VERIFICATION).hasArgument(true).build()),
    LOCATION_SHOULD_BE("Location Should Be", type(VERIFICATION).hasArgument(true).build()),
    PAGE_SHOULD_CONTAIN("Page Should Contain", type(VERIFICATION).hasArgument(true).build()),

    // Browser Keywords
    OPEN_BROWSER("Open Browser", type(BROWSER).hasArgument(true).build()),
    CLOSE_BROWSER("Close Browser", type(BROWSER).build()),
    MAXIMIZE_BROWSER_WINDOW("Maximize Browser Window", type(BROWSER).build()),
    // TODO: For "Go To", use URL defined in Feature Description. (Composing Robot Script)
    GO_TO("Go To", type(BROWSER).hasLocator(false).hasArgument(true).build()),
    SET_SELENIUM_IMPLICIT_WAIT("Set Selenium Implicit Wait", type(BROWSER).hasArgument(true).build()),
    SET_SELENIUM_SPEED("Set Selenium Speed", type(BROWSER).hasArgument(true).build()),
    SET_SELENIUM_TIMEOUT("Set Selenium Timeout", type(BROWSER).hasArgument(true).build());

    private final String keyword;
    private final Properties properties;

    private static Properties.PropertiesBuilder type(Type type) {
        return Properties.builder().type(type);
    }
}
