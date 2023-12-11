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
class UnselectCheckboxRuleTest extends AbstractElementRuleTest {

    @InjectMocks
    private UnselectCheckboxRule rule;

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
            "When I unselect <termsAgreed>",
            "When I deselect <termsAgreed>",
            "When I uncheck <termsAgreed>",
            "When I do not check <termsAgreed>",
            "When I don't check <termsAgreed>"
    );

    private static final Set<String> inaptStepTexts = Set.of(
            "When I select <termsAgreed>",
            "When I check <termsAgreed>",
            "When I choose <termsAgreed>",
            "When I do not uncheck <termsAgreed>",
            "When I don't uncheck <termsAgreed>"
    );

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForAction(
                UnselectCheckboxRule.KEYWORD, aptStepTexts);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForAction();
    }

    private static Stream<Arguments> relationWithIrrelevantWidgetTestCases() {
        return DataProvider.generateRelationStubsWithIrrelevantWidgetForAction(
                UnselectCheckboxRule.KEYWORD);
    }

    private static Stream<Arguments> relationWithInaptStepTextTestCases() {
        return DataProvider.generateRelationStubsForAction(
                UnselectCheckboxRule.KEYWORD, inaptStepTexts);
    }
}
