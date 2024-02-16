package net.kcww.app.robogen.translator.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;

import static net.kcww.app.robogen.translator.model.SeleniumKeyword.Type.*;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumKeywordEnum implements SeleniumKeyword {

    // Element Action Keywords
    CHOOSE_FILE("Choose File", ACTION, hasLocAndArg()),       // locator, file_path
//    CLICK_ELEMENT("Click Element", ACTION, hasLoc()),         // locator, modifier = false (Ignored), action_chain = false (Ignored)
    CLICK_BUTTON("Click Button", ACTION, hasLoc()),           // locator, modifier = false (Ignored)
    CLICK_IMAGE("Click Image", ACTION, hasLoc()),             // locator, modifier = false (Ignored)
    CLICK_LINK("Click Link", ACTION, hasLoc()),               // locator, modifier = false (Ignored)
    INPUT_TEXT("Input Text", ACTION, hasLocAndArg()),         // locator, text, clear = true (Ignored)
    INPUT_PASSWORD("Input Password", ACTION, hasLocAndArg()), // locator, password, clear = true (Ignored)
    SELECT_ALL_FROM_LIST("Select All From List", ACTION, hasLoc()),                     // locator
    UNSELECT_ALL_FROM_LIST("Unselect All From List", ACTION, hasLoc()),                 // locator
    SELECT_FROM_LIST_BY_LABEL("Select From List By Label", ACTION, hasLocAndArg()),     // locator, label(s)
    UNSELECT_FROM_LIST_BY_LABEL("Unselect From List By Label", ACTION, hasLocAndArg()), // locator, labels (This keyword works only with multi-selection lists.)
    SELECT_CHECKBOX("Select Checkbox", ACTION, hasLoc()),               // locator
    UNSELECT_CHECKBOX("Unselect Checkbox", ACTION, hasLoc()),           // locator
    SELECT_RADIO_BUTTON("Select Radio Button", ACTION, hasLocAndArg()), // group_name (locator), value

    // Element Verification Keywords
    ELEMENT_SHOULD_BE_ENABLED("Element Should Be Enabled", VERIFICATION, hasLoc()),         // locator
    ELEMENT_SHOULD_BE_DISABLED("Element Should Be Disabled", VERIFICATION, hasLoc()),       // locator
    ELEMENT_SHOULD_BE_VISIBLE("Element Should Be Visible", VERIFICATION, hasLoc()),         // locator, message = None (Ignored)
    ELEMENT_SHOULD_NOT_BE_VISIBLE("Element Should Not Be Visible", VERIFICATION, hasLoc()), // locator, message = None (Ignored)
    CHECK_BOX_SHOULD_BE_SELECTED("Checkbox Should Be Selected", VERIFICATION, hasLoc()),    // locator
    CHECK_BOX_SHOULD_NOT_BE_SELECTED("Checkbox Should Not Be Selected", VERIFICATION, hasLoc()),        // locator
    RADIO_BUTTON_SHOULD_BE_SET_TO("Radio Button Should Be Set To", VERIFICATION, hasLocAndArg()),       // group_name (locator), value
    RADIO_BUTTON_SHOULD_NOT_BE_SELECTED("Radio Button Should Not Be Selected", VERIFICATION, hasLoc()), // group_name (locator)
    LIST_SELECTION_SHOULD_BE("List Selection Should Be", VERIFICATION, hasLocAndArg()),       // locator, expected
    LIST_SHOULD_HAVE_NO_SELECTIONS("List Should Have No Selections", VERIFICATION, hasLoc()), // locator, expected
    TEXTFIELD_SHOULD_CONTAIN("Textfield Should Contain", VERIFICATION, hasLocAndArg()), // locator, expected, message = None (Ignored)
    TEXTAREA_SHOULD_CONTAIN("Textarea Should Contain", VERIFICATION, hasLocAndArg()),   // locator, expected, message = None (Ignored)

    // Waiting Keywords
    WAIT_UNTIL_ELEMENT_CONTAINS("Wait Until Element Contains", VERIFICATION, hasLocAndArg()),                 // locator, text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_ELEMENT_DOES_NOT_CONTAIN("Wait Until Element Does Not Contain", VERIFICATION, hasLocAndArg()), // locator, text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_ELEMENT_IS_ENABLED("Wait Until Element Is Enabled", VERIFICATION, hasLoc()),         // locator, text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_ELEMENT_IS_VISIBLE("Wait Until Element Is Visible", VERIFICATION, hasLoc()),         // locator, text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE("Wait Until Element Is Not Visible", VERIFICATION, hasLoc()), // locator, text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_LOCATION_IS("Wait Until Location Is", VERIFICATION, hasArg()),                       // str | expected, timeout = None (Ignored), message = None (Ignored)
    WAIT_UNTIL_LOCATION_IS_NOT("Wait Until Location Is Not", VERIFICATION, hasArg()),               // str | location, timeout = None (Ignored), message = None (Ignored)
    WAIT_UNTIL_PAGE_CONTAINS("Wait Until Page Contains", VERIFICATION, hasArg()),                   // text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN("Wait Until Page Does Not Contain", VERIFICATION, hasArg()),   // text, timeout = None (Ignored), error = None (Ignored)
    WAIT_UNTIL_PAGE_CONTAINS_ELEMENT("Wait Until Page Contains Element", VERIFICATION, hasLoc()),                 // locator, timeout = None (Ignored), error = None (Ignored), limit = int | None (Ignored)
    WAIT_UNTIL_PAGE_DOES_NOT_CONTAIN_ELEMENT("Wait Until Page Does Not Contain Element", VERIFICATION, hasLoc()), // locator, timeout = None (Ignored), error = None (Ignored), limit = int | None (Ignored)

    // Page Verification Keywords
    TITLE_SHOULD_BE("Title Should Be", VERIFICATION, hasArg()),         // title, message = None (Ignored)
    LOCATION_SHOULD_BE("Location Should Be", VERIFICATION, hasArg()),   // url, message = None (Ignored)
    PAGE_SHOULD_CONTAIN("Page Should Contain", VERIFICATION, hasArg()), // text, loglevel = TRACE (Ignored)

    // Browser Keywords
    OPEN_BROWSER("Open Browser", BROWSER, hasArg()), // url, browser = firefox, alias = None, remote_url = False, desired_capabilities = None, ff_profile_dir = None, options = None, service_log_path = None, executable_path = None
    CLOSE_BROWSER("Close Browser", BROWSER, noArg()),
    MAXIMIZE_BROWSER_WINDOW("Maximize Browser Window", BROWSER, noArg()),
    GO_TO("Go To", BROWSER, hasArg()),                                           // url
    SET_SELENIUM_IMPLICIT_WAIT("Set Selenium Implicit Wait", BROWSER, hasArg()), // value
    SET_SELENIUM_SPEED("Set Selenium Speed", BROWSER, hasArg()),                 // value
    SET_SELENIUM_TIMEOUT("Set Selenium Timeout", BROWSER, hasArg());             // value

    private final String keyword;
    private final SeleniumKeyword.Type type;
    private final Properties properties;

    private static Properties hasLoc() {
        return Properties.builder().hasLocator(true).build();
    }

    private static Properties hasArg() {
        return Properties.builder().hasArgument(true).build();
    }

    private static Properties hasLocAndArg() {
        return Properties.builder().hasLocator(true).hasArgument(true).build();
    }

    private static Properties noArg() {
        return Properties.builder().build();
    }
}
