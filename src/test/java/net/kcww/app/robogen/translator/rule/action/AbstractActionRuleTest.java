package net.kcww.app.robogen.translator.rule.action;

import io.cucumber.messages.types.StepKeywordType;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.translator.model.widget.Widget;
import net.kcww.app.robogen.translator.rule.action.helper.ActionRuleTestHelper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractActionRuleTest {

    @Mock
    private RelationModel mockRelation;
    @Mock
    private ScenarioStepModel mockScenarioStep;
    @Mock
    private XmlElementModel mockXmlElement;

    protected static Arguments buildRelation(StepKeywordType stepType) {
        return buildRelation(stepType, null);
    }

    protected static Arguments buildRelation(StepKeywordType stepType, Widget widget) {
        return buildRelation(stepType, null, widget);
    }

    protected static Arguments buildRelation(StepKeywordType stepType, String stepText, Widget widget) {
        var scenarioStep = ScenarioStepModel.builder().type(stepType).text(stepText).build();
        var xmlElement = widget != null ? XmlElementModel.builder().tagName(widget.tagName()).build() : null;
        var relation = RelationModel.builder().scenarioStep(scenarioStep).xmlElement(xmlElement).build();
        return Arguments.of(relation);
    }

    protected void runApplicableTest(RelationModel relation, Function<RelationModel, Boolean> isApplicableFunction) {
        var helper = new ActionRuleTestHelper(mockRelation, mockScenarioStep, mockXmlElement);
        helper.setupMocksForApplicableTest(relation);

        boolean actualResult = isApplicableFunction.apply(mockRelation);

        assertThat(actualResult).isTrue();
        helper.verifyInteractionsForApplicableTest();
    }

    protected void runInapplicableActionTest(RelationModel relation,
                                             Function<RelationModel, Boolean> isApplicableFunction) {
        var helper = new ActionRuleTestHelper(mockRelation, mockScenarioStep, mockXmlElement);
        Predicate<StepKeywordType> stepTypePredicate = stepType -> stepType == StepKeywordType.ACTION;
        helper.setupMocksForInapplicableTest(relation, stepTypePredicate);

        boolean actualResult = isApplicableFunction.apply(mockRelation);

        assertThat(actualResult).isFalse();
        helper.verifyInteractionsForInapplicableTest(mockRelation, stepTypePredicate);
    }

    protected void runInapplicableVerificationTest(RelationModel relation,
                                                   Function<RelationModel, Boolean> isApplicableFunction) {
        var helper = new ActionRuleTestHelper(mockRelation, mockScenarioStep, mockXmlElement);
        Predicate<StepKeywordType> stepTypePredicate = stepType -> stepType == StepKeywordType.CONTEXT ||
                stepType == StepKeywordType.OUTCOME;
        helper.setupMocksForInapplicableTest(relation, stepTypePredicate);

        boolean actualResult = isApplicableFunction.apply(mockRelation);

        assertThat(actualResult).isFalse();
        helper.verifyInteractionsForInapplicableTest(mockRelation, stepTypePredicate);
    }

}
