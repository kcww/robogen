package net.kcww.app.robogen.translator.rule;

import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.parser.model.ParsedStep;
import net.kcww.app.robogen.parser.model.XmlElement;
import net.kcww.app.robogen.translator.helper.RelationModelStub;
import net.kcww.app.robogen.translator.model.Keyword;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbstractNonElementRuleTest {

    @Mock
    private StepRelation mockRelation;
    @Mock
    private ParsedStep mockParsedStep;
    @Mock
    private XmlElement mockXmlElement;

    protected void runTest(KeywordRule<StepRelation, Keyword> rule, RelationModelStub relationStub,
                           boolean expectedResult) {
        runTest(rule, relationStub, expectedResult, false);
    }

    protected void runTestWithStepParameters(KeywordRule<StepRelation, Keyword> rule,
                                             RelationModelStub relationStub, boolean expectedResult) {
        runTest(rule, relationStub, expectedResult, true);
    }

    private void runTest(KeywordRule<StepRelation, Keyword> rule, RelationModelStub relationStub,
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
            when(mockRelation.parsedStep()).thenReturn(mockParsedStep);
            when(mockParsedStep.type()).thenReturn(relationStub.stepType());

            if (withStepParameters && relationStub.stepParameters() != null && !relationStub.stepParameters().isEmpty()) {
                when(mockParsedStep.parameters()).thenReturn(relationStub.stepParameters());
                when(mockParsedStep.text()).thenReturn(relationStub.stepText());
            }

            if (!withStepParameters && relationStub.stepText() != null) {
                when(mockParsedStep.text()).thenReturn(relationStub.stepText());
            }
        }
    }

    private void verifyInteractions(RelationModelStub relationStub, boolean withStepParameters) {
        verify(mockRelation, atLeastOnce()).xmlElement();
        if (relationStub.tagName() != null) {
            verify(mockParsedStep, never()).type();
            verify(mockParsedStep, never()).text();
        } else {
            verify(mockParsedStep, atLeastOnce()).type();

            if (withStepParameters && relationStub.stepParameters() != null && !relationStub.stepParameters().isEmpty()) {
                verify(mockParsedStep).parameters();
                verify(mockParsedStep).text();
            }

            if (!withStepParameters && relationStub.stepText() != null) {
                verify(mockParsedStep, relationStub.stepText() != null ? atLeastOnce() : never()).text();
            }
        }
    }
}
