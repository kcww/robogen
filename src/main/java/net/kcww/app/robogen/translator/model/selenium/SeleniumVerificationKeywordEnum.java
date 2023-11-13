package net.kcww.app.robogen.translator.model.selenium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static net.kcww.app.robogen.translator.helper.Tokens.PAGE_SHOULD_CONTAIN_EXCLUDED;
import static net.kcww.app.robogen.translator.helper.Tokens.PAGE_SHOULD_CONTAIN_INCLUDED;
import static net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword.builder;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum SeleniumVerificationKeywordEnum implements SeleniumKeyword {

    TITLE_SHOULD_BE("Title Should Be", builder().hasValue(true).build()),
    LOCATION_SHOULD_BE("Location Should Be", builder().hasValue(true).build()),

    PAGE_SHOULD_CONTAIN("Page Should Contain", builder().hasValue(true)
            .includedTokens(PAGE_SHOULD_CONTAIN_INCLUDED)
            .excludedTokens(PAGE_SHOULD_CONTAIN_EXCLUDED)
            .build());

    private final String keyword;
    private final SeleniumKeyword.Properties properties;
}
