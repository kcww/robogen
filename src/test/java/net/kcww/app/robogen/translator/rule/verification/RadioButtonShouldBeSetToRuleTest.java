package net.kcww.app.robogen.translator.rule.verification;

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
public class RadioButtonShouldBeSetToRuleTest extends AbstractElementRuleTest {

    @InjectMocks
    private RadioButtonShouldBeSetToRule rule;

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
            "Given <smokingPref> is set to \"Smoking\"",
            "Given <smokingPref> is not unset to \"Smoking\""
    );

    private static final Set<String> inaptStepTexts = Set.of(
            "Given <smokingPref> is unset",
            "Given <smokingPref> is not set to \"Smoking\"",
            "Given none of <smokingPref> is set"
    );

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForVerification(
                RadioButtonShouldBeSetToRule.KEYWORD, aptStepTexts);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForVerification();
    }

    private static Stream<Arguments> relationWithIrrelevantWidgetTestCases() {
        return DataProvider.generateRelationStubsWithIrrelevantWidgetForVerification(
                RadioButtonShouldBeSetToRule.KEYWORD);
    }

    private static Stream<Arguments> relationWithInaptStepTextTestCases() {
        return DataProvider.generateRelationStubsForVerification(
                RadioButtonShouldBeSetToRule.KEYWORD, inaptStepTexts);
    }
}
