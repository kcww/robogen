package net.kcww.app.robogen.translator.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.translator.helper.Words;

import java.util.List;

@Data
@Builder
@Accessors(fluent = true)
public class Keyword {

    @NonNull
    private String keyword;
    @Singular
    private List<Argument> arguments;

    @Data
    @Builder
    @Accessors(fluent = true)
    public static class Argument {

        public enum Type {
            LOCATOR, PARAMETER, LITERAL
        }

        @NonNull
        private String value;
        @NonNull
        private final Type type;

        @Override
        public String toString() {
            return type == Type.PARAMETER ? String.format(Words.DEFAULT_VALUE_TEMPLATE, value) : value;
        }
    }
}
