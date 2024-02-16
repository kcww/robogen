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
public class CheckboxShouldNotBeSelectedRuleTest extends AbstractElementRuleTest {

    @InjectMocks
    private CheckboxShouldNotBeSelectedRule rule;

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
            "Given <termsAgreed> is unselect",
            "Given <termsAgreed> is deselect",
            "Given <termsAgreed> is uncheck",
            "Given <termsAgreed> is not check",
            "Given <termsAgreed> isn't check"
    );

    private static final Set<String> inaptStepTexts = Set.of(
            "Given <termsAgreed> is selected",
            "Given <termsAgreed> is checked",
            "Given <termsAgreed> is chosen",
            "Given <termsAgreed> is not unchecked",
            "Given <termsAgreed> isn't unchecked"
    );

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForVerification(
                CheckboxShouldNotBeSelectedRule.SELENIUM_KEYWORD, aptStepTexts);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForVerification();
    }

    private static Stream<Arguments> relationWithIrrelevantWidgetTestCases() {
        return DataProvider.generateRelationStubsWithIrrelevantWidgetForVerification(
                CheckboxShouldNotBeSelectedRule.SELENIUM_KEYWORD);
    }

    private static Stream<Arguments> relationWithInaptStepTextTestCases() {
        return DataProvider.generateRelationStubsForVerification(
                CheckboxShouldNotBeSelectedRule.SELENIUM_KEYWORD, inaptStepTexts);
    }
}
