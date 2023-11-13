package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword.builder;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumBrowserKeywordEnum implements SeleniumKeyword {

    OPEN_BROWSER("Open Browser", builder().hasValue(true).build()),
    CLOSE_BROWSER("Close Browser", builder().build()),
    MAXIMIZE_BROWSER_WINDOW("Maximize Browser Window", builder().build()),

    // TODO: For "Go To", use URL defined in Feature Description. (Composing Robot Script)
    GO_TO("Go To", builder().hasValue(true).build()),

    SET_SELENIUM_IMPLICIT_WAIT("Set Selenium Implicit Wait", builder().hasValue(true).build()),
    SET_SELENIUM_SPEED("Set Selenium Speed", builder().hasValue(true).build()),
    SET_SELENIUM_TIMEOUT("Set Selenium Timeout", builder().hasValue(true).build());

    private final String keyword;
    private final SeleniumKeyword.Properties properties;
}
