package net.kcww.app.robogen.translator.model;

import lombok.Builder;
import net.kcww.app.robogen.parser.model.ParsedStep;

public interface SeleniumKeyword {

    String keyword();
    Type type();
    Properties properties();

    default boolean isApplicable(ParsedStep.Type stepType) {
        return type().matches(stepType);
    }

    enum Type {
        ACTION, VERIFICATION, BROWSER;

        private boolean matches(ParsedStep.Type stepType) {
            return switch (this) {
                case ACTION -> stepType == ParsedStep.Type.WHEN;
                case VERIFICATION -> stepType == ParsedStep.Type.GIVEN || stepType == ParsedStep.Type.THEN;
                case BROWSER -> false;
            };
        }
    }

    @Builder
    record Properties(boolean hasLocator, boolean hasArgument) {
    }
}
