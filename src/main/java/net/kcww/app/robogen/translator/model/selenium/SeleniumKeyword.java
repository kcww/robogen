package net.kcww.app.robogen.translator.model.selenium;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

public interface SeleniumKeyword {

    String keyword();
    SeleniumKeyword.Properties properties();

    static SeleniumKeyword.Properties.PropertiesBuilder builder() {
        return SeleniumKeyword.Properties.builder();
    }

    @Builder
    @Getter
    @Accessors(fluent = true)
    final class Properties {

        @Builder.Default
        private boolean hasLocator = false;
        @Builder.Default
        private boolean hasValue = false;

        @Singular
        private final List<List<Set<String>>> includedTokens;

        @Singular
        private final List<List<Set<String>>> excludedTokens;
    }
}