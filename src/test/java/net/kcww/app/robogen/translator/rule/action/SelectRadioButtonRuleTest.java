package net.kcww.app.robogen.translator.rule.action;

import io.cucumber.messages.types.StepKeywordType;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.widget.GwtWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.VaadinWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.Widget;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

import static io.cucumber.messages.types.StepKeywordType.*;

@ExtendWith(MockitoExtension.class)
public class SelectRadioButtonRuleTest extends AbstractActionRuleTest {

    @InjectMocks
    private SelectRadioButtonRule selectRadioButtonRule;

    @ParameterizedTest
    @MethodSource("applicableTestCases")
    public void isApplicableTest(RelationModel relation) {
        runApplicableTest(relation, mock -> selectRadioButtonRule.isApplicable(mock));
    }

    @ParameterizedTest
    @MethodSource("inapplicableTestCases")
    public void isInapplicableTest(RelationModel relation) {
        runInapplicableActionTest(relation, mock -> selectRadioButtonRule.isApplicable(mock));
    }

    private static Set<String> aptStepTexts = Set.of(
            "And I select <smokingPref>",
            "And I check <smokingPref>",
            "And I choose <smokingPref>",
            "And I do not deselect <smokingPref>",
            "And I don't deselect <smokingPref>"
    );

    private static Set<String> inaptStepTexts = Set.of(
            "And I unselect <smokingPref>",
            "And I deselect <smokingPref>",
            "And I uncheck <smokingPref>",
            "And I do not check <smokingPref>",
            "And I don't check <smokingPref>"
    );

    private static Set<Widget> widgets = Set.of(
            GwtWidgetEnum.RADIO_BUTTON,
            GwtWidgetEnum.SIMPLE_RADIO_BUTTON,
            VaadinWidgetEnum.RADIO_GROUP
    );

    private static Stream<Arguments> applicableTestCases() {
        return widgets.stream().flatMap(widget -> aptStepTexts.stream().map(text -> buildRelation(ACTION, text, widget)));
    }

    private static Stream<Arguments> inapplicableTestCases() {
        var aptStepTextNonActionCases = widgets.stream().flatMap(widget -> aptStepTexts.stream()
                .flatMap(text -> Stream.of(buildRelation(CONTEXT, text, widget), buildRelation(OUTCOME, text, widget))));

        var inaptStepTextActionCases = widgets.stream().flatMap(widget -> inaptStepTexts.stream()
                .flatMap(text -> Stream.of(buildRelation(ACTION, text, widget))));

        var noWidgetCases = Stream.of(StepKeywordType.values()).map(stepType -> buildRelation(stepType));

        var differentWidgetCases = Stream.concat(
                Stream.of(GwtWidgetEnum.values()).filter(widget -> !widgets.contains(widget))
                        .map(widget -> buildRelation(ACTION, widget)),
                Stream.of(VaadinWidgetEnum.values()).filter(widget -> !widgets.contains(widget))
                        .map(widget -> buildRelation(ACTION, widget))
        );

        var stepTextCases = Stream.concat(aptStepTextNonActionCases, inaptStepTextActionCases);
        var otherWidgetCases = Stream.concat(noWidgetCases, differentWidgetCases);
        return Stream.concat(stepTextCases, otherWidgetCases);
    }
}
