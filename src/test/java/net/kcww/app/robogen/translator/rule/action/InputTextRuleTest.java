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
public class InputTextRuleTest extends AbstractActionRuleTest {

    @InjectMocks
    private InputTextRule inputTextRule;

    @ParameterizedTest
    @MethodSource("applicableTestCases")
    public void isApplicableTest(RelationModel relation) {
        runApplicableTest(relation, mock -> inputTextRule.isApplicable(mock));
    }

    @ParameterizedTest
    @MethodSource("inapplicableTestCases")
    public void isInapplicableTest(RelationModel relation) {
        runInapplicableActionTest(relation, mock -> inputTextRule.isApplicable(mock));
    }

    private static Set<Widget> widgets = Set.of(
            GwtWidgetEnum.DATEBOX,
            GwtWidgetEnum.DOUBLEBOX,
            GwtWidgetEnum.INTEGERBOX,
            GwtWidgetEnum.LONGBOX,
            GwtWidgetEnum.RICHTEXTAREA,
            GwtWidgetEnum.SUGGESTBOX,
            GwtWidgetEnum.TEXTBOX,
            GwtWidgetEnum.TEXTAREA,
            VaadinWidgetEnum.DATE_PICKER,
            VaadinWidgetEnum.INTEGER_FIELD,
            VaadinWidgetEnum.TEXT_FIELD,
            VaadinWidgetEnum.TEXT_AREA
    );

    private static Stream<Arguments> applicableTestCases() {
        return widgets.stream().map(widget -> buildRelation(ACTION, widget));
    }

    private static Stream<Arguments> inapplicableTestCases() {
        var noWidgetCases = Stream.of(StepKeywordType.values()).map(stepType -> buildRelation(stepType));
        var differentWidgetCases = Stream.concat(
                Stream.of(GwtWidgetEnum.values()).filter(widget -> !widgets.contains(widget))
                        .map(widget -> buildRelation(ACTION, widget)),
                Stream.of(VaadinWidgetEnum.values()).filter(widget -> !widgets.contains(widget))
                        .map(widget -> buildRelation(ACTION, widget))
        );
        return Stream.concat(noWidgetCases, differentWidgetCases);
    }
}
