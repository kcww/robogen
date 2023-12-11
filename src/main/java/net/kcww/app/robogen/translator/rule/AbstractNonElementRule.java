package net.kcww.app.robogen.translator.rule;

import io.cucumber.messages.types.StepKeywordType;
import lombok.RequiredArgsConstructor;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.helper.Words;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;

@RequiredArgsConstructor
public abstract class AbstractNonElementRule implements KeywordRule<RelationModel, KeywordModel> {

    private final SeleniumKeyword seleniumKeyword;

    @Override
    public boolean isApplicable(RelationModel relation) {
        if (relation.xmlElement() !=null) return false;
        var stepType = relation.scenarioStep().type();
        return stepType == StepKeywordType.CONTEXT || stepType == StepKeywordType.OUTCOME;
    }

    @Override
    public KeywordModel translate(RelationModel relation) {
        return KeywordModel.builder()
                .keyword(seleniumKeyword.keyword())
                .value(seleniumKeyword.properties().hasArgument() ? getValue(relation) : null)
                .build();
    }

    protected String getValue(RelationModel relation) {
        return relation.scenarioStep().literals().stream().findFirst().orElse(Words.ROBOT_EMPTY);
    }
}
