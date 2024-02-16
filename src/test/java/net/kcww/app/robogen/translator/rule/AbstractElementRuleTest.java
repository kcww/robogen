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
public abstract class AbstractElementRuleTest {

    @Mock
    private StepRelation mockRelation;
    @Mock
    private ParsedStep mockParsedStep;
    @Mock
    private XmlElement mockXmlElement;

    protected void runTest(KeywordRule<StepRelation, Keyword> rule, RelationModelStub relationStub, boolean expectedResult) {
        setupMocks(relationStub);

        boolean actualResult = rule.isApplicable(mockRelation);

        assertThat(actualResult).isEqualTo(expectedResult);
        verifyInteractions(relationStub);
    }

    private void setupMocks(RelationModelStub relationStub) {
        if (relationStub == null) return;
        when(mockRelation.xmlElement()).thenReturn(mockXmlElement);
        when(mockRelation.parsedStep()).thenReturn(mockParsedStep);
        when(mockParsedStep.type()).thenReturn(relationStub.stepType());
        setupOptionalMocks(relationStub);
    }

    private void setupOptionalMocks(RelationModelStub relationStub) {
        if (relationStub.tagName() != null) {
            when(mockXmlElement.tagName()).thenReturn(relationStub.tagName());
        }
        if (relationStub.stepText() != null) {
            when(mockParsedStep.text()).thenReturn(relationStub.stepText());
        }
    }

    private void verifyInteractions(RelationModelStub relationStub) {
        verify(mockRelation, atLeastOnce()).xmlElement();
        verifyOptionalInteractions(relationStub);
    }

    private void verifyOptionalInteractions(RelationModelStub relationStub) {
        if (relationStub != null) {
            verify(mockParsedStep, atLeastOnce()).type();
            verify(mockXmlElement, relationStub.tagName() != null ? atLeastOnce() : never()).tagName();
            verify(mockParsedStep, relationStub.stepText() != null ? atLeastOnce() : never()).text();
        } else {
            verify(mockParsedStep, never()).type();
            verify(mockXmlElement, never()).tagName();
            verify(mockParsedStep, never()).text();
        }
    }
}
