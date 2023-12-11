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
public abstract class AbstractElementRuleTest {

    @Mock
    private RelationModel mockRelation;
    @Mock
    private ScenarioStepModel mockScenarioStep;
    @Mock
    private XmlElementModel mockXmlElement;

    protected void runTest(KeywordRule<RelationModel, KeywordModel> rule, RelationModelStub relationStub, boolean expectedResult) {
        setupMocks(relationStub);

        boolean actualResult = rule.isApplicable(mockRelation);

        assertThat(actualResult).isEqualTo(expectedResult);
        verifyInteractions(relationStub);
    }

    private void setupMocks(RelationModelStub relationStub) {
        if (relationStub == null) return;
        when(mockRelation.xmlElement()).thenReturn(mockXmlElement);
        when(mockRelation.scenarioStep()).thenReturn(mockScenarioStep);
        when(mockScenarioStep.type()).thenReturn(relationStub.stepType());
        setupOptionalMocks(relationStub);
    }

    private void setupOptionalMocks(RelationModelStub relationStub) {
        if (relationStub.tagName() != null) {
            when(mockXmlElement.tagName()).thenReturn(relationStub.tagName());
        }
        if (relationStub.stepText() != null) {
            when(mockScenarioStep.text()).thenReturn(relationStub.stepText());
        }
    }

    private void verifyInteractions(RelationModelStub relationStub) {
        verify(mockRelation, atLeastOnce()).xmlElement();
        verifyOptionalInteractions(relationStub);
    }

    private void verifyOptionalInteractions(RelationModelStub relationStub) {
        if (relationStub != null) {
            verify(mockScenarioStep, atLeastOnce()).type();
            verify(mockXmlElement, relationStub.tagName() != null ? atLeastOnce() : never()).tagName();
            verify(mockScenarioStep, relationStub.stepText() != null ? atLeastOnce() : never()).text();
        } else {
            verify(mockScenarioStep, never()).type();
            verify(mockXmlElement, never()).tagName();
            verify(mockScenarioStep, never()).text();
        }
    }
}
