package net.kcww.app.robogen.translator.model.selenium;

import lombok.Builder;

public interface SeleniumKeyword {

    enum Type {
        ACTION, VERIFICATION, BROWSER
    }

    String keyword();
    Properties properties();

    @Builder
    record Properties(SeleniumKeyword.Type type, boolean hasLocator, boolean hasArgument) {}
}