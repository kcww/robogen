package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumVerificationKeywordEnum implements SeleniumKeyword {

    TITLE_SHOULD_BE("Title Should Be", NO_LOC, HAS_ARG),
    LOCATION_SHOULD_BE("Location Should Be", NO_LOC, HAS_ARG),
    PAGE_SHOULD_CONTAIN("Page Should Contain", NO_LOC, HAS_ARG);

    private final String keyword;
    private final boolean hasLocator;
    private final boolean hasArgument;
}
