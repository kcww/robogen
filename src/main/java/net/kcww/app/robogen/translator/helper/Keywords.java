package net.kcww.app.robogen.translator.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.common.helper.Texts;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.model.Keyword.Argument.Type;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Keywords {

    public static Optional<Keyword.Argument> determineArgument(String text) {
        return Texts.findFirstStepParameter(text)
                .map(s -> newArg(s, Type.PARAMETER))
                .or(() -> Texts.findFirstStepLiteral(text).map(s -> newArg(s, Type.LITERAL)))
                .or(() -> Optional.of(newArg(Words.ROBOT_EMPTY, Type.LITERAL)));
    }

    public static Optional<Keyword.Argument> determineUrlArgument(String text) {
        return Texts.findFirstUrl(text)
                .map(url -> newArg(url, Type.LITERAL));
    }

    public static Keyword.Argument newArg(String value, Type type) {
        return Keyword.Argument.builder().value(value).type(type).build();
    }
}
