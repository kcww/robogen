package net.kcww.app.robogen.translator.rule;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.translator.helper.RelationModelStub;
import net.kcww.app.robogen.translator.model.KeywordModel;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbstractNonElementRuleTest {

    @Mock
    private RelationModel mockRelation;
    @Mock
    private ScenarioStepModel mockScenarioStep;
    @Mock
    private XmlElementModel mockXmlElement;

    protected void runTest(KeywordRule<RelationModel, KeywordModel> rule, RelationModelStub relationStub,
                           boolean expectedResult) {
        runTest(rule, relationStub, expectedResult, false);
    }

    protected void runTestWithStepParameters(KeywordRule<RelationModel, KeywordModel> rule,
                                             RelationModelStub relationStub, boolean expectedResult) {
        runTest(rule, relationStub, expectedResult, true);
    }

    private void runTest(KeywordRule<RelationModel, KeywordModel> rule, RelationModelStub relationStub,
                         boolean expectedResult, boolean withStepParameters) {
        setupMocks(relationStub, withStepParameters);

        boolean actualResult = rule.isApplicable(mockRelation);

        assertThat(actualResult).isEqualTo(expectedResult);
        verifyInteractions(relationStub, withStepParameters);
    }

    private void setupMocks(RelationModelStub relationStub, boolean withStepParameters) {
        if (relationStub.tagName() != null) {
            when(mockRelation.xmlElement()).thenReturn(mockXmlElement);
        } else {
            when(mockRelation.scenarioStep()).thenReturn(mockScenarioStep);
            when(mockScenarioStep.type()).thenReturn(relationStub.stepType());

            if (withStepParameters && relationStub.stepParameters() != null && !relationStub.stepParameters().isEmpty()) {
                when(mockScenarioStep.parameters()).thenReturn(relationStub.stepParameters());
                when(mockScenarioStep.text()).thenReturn(relationStub.stepText());
            }

            if (!withStepParameters && relationStub.stepText() != null) {
                when(mockScenarioStep.text()).thenReturn(relationStub.stepText());
            }
        }
    }

    private void verifyInteractions(RelationModelStub relationStub, boolean withStepParameters) {
        verify(mockRelation, atLeastOnce()).xmlElement();
        if (relationStub.tagName() != null) {
            verify(mockScenarioStep, never()).type();
            verify(mockScenarioStep, never()).text();
        } else {
            verify(mockScenarioStep, atLeastOnce()).type();

            if (withStepParameters && relationStub.stepParameters() != null && !relationStub.stepParameters().isEmpty()) {
                verify(mockScenarioStep).parameters();
                verify(mockScenarioStep).text();
            }

            if (!withStepParameters && relationStub.stepText() != null) {
                verify(mockScenarioStep, relationStub.stepText() != null ? atLeastOnce() : never()).text();
            }
        }
    }
}
