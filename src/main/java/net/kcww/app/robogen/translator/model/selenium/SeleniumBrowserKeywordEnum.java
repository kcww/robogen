package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumBrowserKeywordEnum implements SeleniumKeyword {

    OPEN_BROWSER("Open Browser", NO_LOC, HAS_ARG),
    CLOSE_BROWSER("Close Browser", NO_LOC, NO_ARG),
    MAXIMIZE_BROWSER_WINDOW("Maximize Browser Window", NO_LOC, NO_ARG),

    // TODO: For "Go To", use URL defined in Feature Description. (Composing Robot Script)
    GO_TO("Go To", NO_LOC, HAS_ARG),

    SET_SELENIUM_IMPLICIT_WAIT("Set Selenium Implicit Wait", NO_LOC, HAS_ARG),
    SET_SELENIUM_SPEED("Set Selenium Speed", NO_LOC, HAS_ARG),
    SET_SELENIUM_TIMEOUT("Set Selenium Timeout", NO_LOC, HAS_ARG);

    private final String keyword;
    private final boolean hasLocator;
    private final boolean hasArgument;
}
