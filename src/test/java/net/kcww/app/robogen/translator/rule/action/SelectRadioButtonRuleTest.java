package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.helper.DataProvider;
import net.kcww.app.robogen.translator.helper.RelationModelStub;
import net.kcww.app.robogen.translator.rule.AbstractElementRuleTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class SelectRadioButtonRuleTest extends AbstractElementRuleTest {

    @InjectMocks
    private SelectRadioButtonRule rule;

    @ParameterizedTest
    @MethodSource("elicitableRelationTestCases")
    public void elicitableRelationTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, true);
    }

    @Test
    public void relationWithNoWidgetTest() {
        runTest(rule, null, false);
    }

    @ParameterizedTest
    @MethodSource("relationWithInaptStepTypeTestCases")
    public void relationWithInaptStepTypeTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    @ParameterizedTest
    @MethodSource("relationWithIrrelevantWidgetTestCases")
    public void relationWithIrrelevantWidgetTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    @ParameterizedTest
    @MethodSource("relationWithInaptStepTextTestCases")
    public void relationWithInaptStepTextTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    private static final Set<String> aptStepTexts = Set.of(
            "When I select <smokingPref>",
            "When I check <smokingPref>",
            "When I choose <smokingPref>",
            "When I do not deselect <smokingPref>",
            "When I don't deselect <smokingPref>"
    );

    private static final Set<String> inaptStepTexts = Set.of(
            "When I unselect <smokingPref>",
            "When I deselect <smokingPref>",
            "When I uncheck <smokingPref>",
            "When I do not check <smokingPref>",
            "When I don't check <smokingPref>"
    );

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForAction(
                SelectRadioButtonRule.KEYWORD, aptStepTexts);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForAction();
    }

    private static Stream<Arguments> relationWithIrrelevantWidgetTestCases() {
        return DataProvider.generateRelationStubsWithIrrelevantWidgetForAction(
                SelectRadioButtonRule.KEYWORD);
    }

    private static Stream<Arguments> relationWithInaptStepTextTestCases() {
        return DataProvider.generateRelationStubsForAction(
                SelectRadioButtonRule.KEYWORD, inaptStepTexts);
    }
}
