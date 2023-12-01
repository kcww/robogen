package net.kcww.app.robogen.translator.rule.action.helper;

import io.cucumber.messages.types.StepKeywordType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;

import java.util.function.Predicate;

import static org.mockito.Mockito.*;

@Builder
@RequiredArgsConstructor
public class ActionRuleTestHelper {

    private final RelationModel mockRelation;
    private final ScenarioStepModel mockScenarioStep;
    private final XmlElementModel mockXmlElement;

    public void setupMocks(String tagName, StepKeywordType stepType) {
        if (tagName == null) {
            when(mockRelation.xmlElement()).thenReturn(null);
        } else {
            when(mockRelation.xmlElement()).thenReturn(mockXmlElement);

            when(mockRelation.scenarioStep()).thenReturn(mockScenarioStep);
            when(mockScenarioStep.type()).thenReturn(stepType);

            if (stepType == StepKeywordType.ACTION) {
                when(mockXmlElement.tagName()).thenReturn(tagName);
            }
        }
    }

    public void verifyInteractions(String tagName, StepKeywordType stepType) {
        if (tagName == null) {
            verify(mockRelation).xmlElement();
            verifyNoInteractions(mockScenarioStep);
            verifyNoInteractions(mockXmlElement);
        } else {
            verify(mockScenarioStep).type();
            if (stepType == StepKeywordType.ACTION) {
                verify(mockXmlElement, atLeastOnce()).tagName();
            }
        }
    }

    public void setupMocksForApplicableTest(RelationModel relation) {
        when(mockRelation.xmlElement()).thenReturn(mockXmlElement);

        when(mockRelation.scenarioStep()).thenReturn(mockScenarioStep);
        when(mockScenarioStep.type()).thenReturn(relation.scenarioStep().type());

        when(mockXmlElement.tagName()).thenReturn(relation.xmlElement().tagName());
        when(mockScenarioStep.text()).thenReturn(relation.scenarioStep().text());
    }

    public void verifyInteractionsForApplicableTest() {
        verify(mockRelation).xmlElement();
        verify(mockScenarioStep).type();

        verify(mockXmlElement, atLeastOnce()).tagName();
        verify(mockScenarioStep, atLeastOnce()).text();
    }

    public void setupMocksForInapplicableTest(RelationModel relation, Predicate<StepKeywordType> stepTypePredicate) {
        if (relation.xmlElement() == null) {
            when(mockRelation.xmlElement()).thenReturn(null);
        } else {
            when(mockRelation.xmlElement()).thenReturn(mockXmlElement);

            var stepType = relation.scenarioStep().type();
            when(mockRelation.scenarioStep()).thenReturn(mockScenarioStep);
            when(mockScenarioStep.type()).thenReturn(stepType);

            if (stepTypePredicate.test(stepType)) {
                when(mockXmlElement.tagName()).thenReturn(relation.xmlElement().tagName());
                lenient().when(mockScenarioStep.text()).thenReturn(relation.scenarioStep().text());
            }
        }
    }

    public void verifyInteractionsForInapplicableTest(RelationModel relation, Predicate<StepKeywordType> stepTypePredicate) {
        verify(mockRelation).xmlElement();
        if (relation.xmlElement() != null)  {
            verify(mockScenarioStep).type();
            if (stepTypePredicate.test(relation.scenarioStep().type())) {
                verify(mockXmlElement, atLeastOnce()).tagName();
            }
        }
    }
}
