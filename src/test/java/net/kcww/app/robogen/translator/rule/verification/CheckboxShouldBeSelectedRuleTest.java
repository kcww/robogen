package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.widget.GwtWidgetEnum;
import net.kcww.app.robogen.translator.model.widget.VaadinWidgetEnum;
import net.kcww.app.robogen.translator.rule.action.AbstractActionRuleTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static io.cucumber.messages.types.StepKeywordType.*;

@ExtendWith(MockitoExtension.class)
public class CheckboxShouldBeSelectedRuleTest extends AbstractActionRuleTest {

    @InjectMocks
    private CheckboxShouldBeSelectedRule checkboxShouldBeSelectedRule;

    @ParameterizedTest
    @MethodSource("applicableTestCases")
    public void isApplicableTest(RelationModel relation) {
        runApplicableTest(relation, mock -> checkboxShouldBeSelectedRule.isApplicable(mock));
    }

    @ParameterizedTest
    @MethodSource("inapplicableTestCases")
    public void isInapplicableTest(RelationModel relation) {
        runInapplicableVerificationTest(relation, mock -> checkboxShouldBeSelectedRule.isApplicable(mock));
    }

    private static Stream<Arguments> applicableTestCases() {
        return Stream.of(
                buildRelation(CONTEXT, "And <termsAgreed> is checked", GwtWidgetEnum.CHECKBOX),
                buildRelation(CONTEXT, "And <termsAgreed> is not unchecked", GwtWidgetEnum.CHECKBOX),

                buildRelation(CONTEXT, "And <termsAgreed> is checked", GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(CONTEXT, "And <termsAgreed> is checked", VaadinWidgetEnum.CHECKBOX),

                buildRelation(OUTCOME, "And <termsAgreed> is checked", GwtWidgetEnum.CHECKBOX),
                buildRelation(OUTCOME, "And <termsAgreed> is not unchecked", GwtWidgetEnum.CHECKBOX),

                buildRelation(OUTCOME, "And <termsAgreed> is checked", GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(OUTCOME, "And <termsAgreed> is checked", VaadinWidgetEnum.CHECKBOX)
        );
    }

    private static Stream<Arguments> inapplicableTestCases() {
        return Stream.of(
                buildRelation(ACTION, "And <termsAgreed> is checked", GwtWidgetEnum.CHECKBOX),
                buildRelation(ACTION, "And <termsAgreed> is not unchecked", GwtWidgetEnum.CHECKBOX),

                buildRelation(ACTION, "And <termsAgreed> is checked", GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(ACTION, "And <termsAgreed> is checked", VaadinWidgetEnum.CHECKBOX),

                buildRelation(CONTEXT, "And <termsAgreed> is unchecked", GwtWidgetEnum.CHECKBOX),
                buildRelation(CONTEXT, "And <termsAgreed> is not checked", GwtWidgetEnum.CHECKBOX),

                buildRelation(CONTEXT, "And <termsAgreed> is unchecked", GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(CONTEXT, "And <termsAgreed> is unchecked", VaadinWidgetEnum.CHECKBOX),

                buildRelation(OUTCOME, "And <termsAgreed> is unchecked", GwtWidgetEnum.CHECKBOX),
                buildRelation(OUTCOME, "And <termsAgreed> is not checked", GwtWidgetEnum.CHECKBOX),

                buildRelation(OUTCOME, "And <termsAgreed> is unchecked", GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(OUTCOME, "And <termsAgreed> is unchecked", VaadinWidgetEnum.CHECKBOX),

                buildRelation(ACTION),
                buildRelation(ACTION, GwtWidgetEnum.CHECKBOX),
                buildRelation(ACTION, GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(ACTION, VaadinWidgetEnum.CHECKBOX),

                buildRelation(CONTEXT),
                buildRelation(CONTEXT, GwtWidgetEnum.BUTTON),
                buildRelation(CONTEXT, GwtWidgetEnum.PUSH_BUTTON),
                buildRelation(CONTEXT, GwtWidgetEnum.RESET_BUTTON),
                buildRelation(CONTEXT, GwtWidgetEnum.SUBMIT_BUTTON),
                buildRelation(CONTEXT, GwtWidgetEnum.TOGGLE_BUTTON),
//                buildRelation(CONTEXT, GwtWidgetEnum.CHECKBOX),
//                buildRelation(CONTEXT, GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.FILE_UPLOAD),
                buildRelation(CONTEXT, GwtWidgetEnum.IMAGE),
                buildRelation(CONTEXT, GwtWidgetEnum.ANCHOR),
                buildRelation(CONTEXT, GwtWidgetEnum.LISTBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.PASSWORD_TEXTBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.RADIO_BUTTON),
                buildRelation(CONTEXT, GwtWidgetEnum.SIMPLE_RADIO_BUTTON),
                buildRelation(CONTEXT, GwtWidgetEnum.DATEBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.DOUBLEBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.INTEGERBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.LONGBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.RICHTEXTAREA),
                buildRelation(CONTEXT, GwtWidgetEnum.SUGGESTBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.TEXTBOX),
                buildRelation(CONTEXT, GwtWidgetEnum.TEXTAREA),

                buildRelation(OUTCOME, VaadinWidgetEnum.BUTTON),
//                buildRelation(OUTCOME, VaadinWidgetEnum.CHECKBOX),
                buildRelation(CONTEXT, VaadinWidgetEnum.UPLOAD),
                buildRelation(CONTEXT, VaadinWidgetEnum.IMAGE),
                buildRelation(CONTEXT, VaadinWidgetEnum.COMBOBOX),
                buildRelation(CONTEXT, VaadinWidgetEnum.SELECT),
                buildRelation(CONTEXT, VaadinWidgetEnum.PASSWORD_FIELD),
                buildRelation(CONTEXT, VaadinWidgetEnum.RADIO_GROUP),
                buildRelation(CONTEXT, VaadinWidgetEnum.DATE_PICKER),
                buildRelation(CONTEXT, VaadinWidgetEnum.INTEGER_FIELD),
                buildRelation(CONTEXT, VaadinWidgetEnum.TEXT_FIELD),
                buildRelation(CONTEXT, VaadinWidgetEnum.TEXT_AREA),

                buildRelation(OUTCOME),
                buildRelation(OUTCOME, GwtWidgetEnum.BUTTON),
                buildRelation(OUTCOME, GwtWidgetEnum.PUSH_BUTTON),
                buildRelation(OUTCOME, GwtWidgetEnum.RESET_BUTTON),
                buildRelation(OUTCOME, GwtWidgetEnum.SUBMIT_BUTTON),
                buildRelation(OUTCOME, GwtWidgetEnum.TOGGLE_BUTTON),
//                buildRelation(OUTCOME, GwtWidgetEnum.CHECKBOX),
//                buildRelation(OUTCOME, GwtWidgetEnum.SIMPLE_CHECKBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.FILE_UPLOAD),
                buildRelation(OUTCOME, GwtWidgetEnum.IMAGE),
                buildRelation(OUTCOME, GwtWidgetEnum.ANCHOR),
                buildRelation(OUTCOME, GwtWidgetEnum.LISTBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.PASSWORD_TEXTBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.RADIO_BUTTON),
                buildRelation(OUTCOME, GwtWidgetEnum.SIMPLE_RADIO_BUTTON),
                buildRelation(OUTCOME, GwtWidgetEnum.DATEBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.DOUBLEBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.INTEGERBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.LONGBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.RICHTEXTAREA),
                buildRelation(OUTCOME, GwtWidgetEnum.SUGGESTBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.TEXTBOX),
                buildRelation(OUTCOME, GwtWidgetEnum.TEXTAREA),

                buildRelation(OUTCOME, VaadinWidgetEnum.BUTTON),
//                buildRelation(OUTCOME, VaadinWidgetEnum.CHECKBOX),
                buildRelation(OUTCOME, VaadinWidgetEnum.UPLOAD),
                buildRelation(OUTCOME, VaadinWidgetEnum.IMAGE),
                buildRelation(OUTCOME, VaadinWidgetEnum.COMBOBOX),
                buildRelation(OUTCOME, VaadinWidgetEnum.SELECT),
                buildRelation(OUTCOME, VaadinWidgetEnum.PASSWORD_FIELD),
                buildRelation(OUTCOME, VaadinWidgetEnum.RADIO_GROUP),
                buildRelation(OUTCOME, VaadinWidgetEnum.DATE_PICKER),
                buildRelation(OUTCOME, VaadinWidgetEnum.INTEGER_FIELD),
                buildRelation(OUTCOME, VaadinWidgetEnum.TEXT_FIELD),
                buildRelation(OUTCOME, VaadinWidgetEnum.TEXT_AREA)
        );
    }
}
