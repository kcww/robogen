package net.kcww.app.robogen.translator.rule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.parser.model.ParsedStep;
import net.kcww.app.robogen.translator.helper.Keywords;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;

import java.util.Optional;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public abstract class AbstractNonElementRule implements KeywordRule<StepRelation, Keyword> {

    private final SeleniumKeyword seleniumKeyword;

    @Override
    public boolean isApplicable(StepRelation relation) {
        return relation.xmlElement() == null &&
                isVerificationStep(relation.parsedStep().type());
    }

    protected boolean isVerificationStep(ParsedStep.Type type) {
        return type == ParsedStep.Type.GIVEN || type == ParsedStep.Type.THEN;
    }

    @Override
    public Keyword translate(StepRelation relation) {
        return buildKeyword(relation, Keywords::determineArgument);
    }

    protected Keyword buildKeyword(StepRelation relation, Function<String, Optional<Keyword.Argument>> argFunc) {
        var builder = Keyword.builder().keyword(seleniumKeyword.keyword());

        if (seleniumKeyword.properties().hasArgument()) {
            argFunc.apply(relation.parsedStep().text()).ifPresent(builder::argument);
        }
        return builder.build();
    }
}
