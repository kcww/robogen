package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.DataProvider;
import net.kcww.app.robogen.translator.helper.RelationModelStub;
import net.kcww.app.robogen.translator.rule.AbstractElementRuleTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ElementShouldBeDisabledRuleTest extends AbstractElementRuleTest {

    @InjectMocks
    private ElementShouldBeDisabledRule rule;

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
    @NullSource
    public void relationShouldBeNullForIrrelevantWidgetsTest(RelationModelStub relationStub) {
        assertThat(relationStub).isNull();
    }

    @ParameterizedTest
    @MethodSource("relationWithInaptStepTextTestCases")
    public void relationWithInaptStepTextTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    private static final Set<String> aptStepTexts = Set.of(
            "Given <element> is disabled",
            "Given <element> is not enabled"
    );

    private static final Set<String> inaptStepTexts = Set.of(
            "Given <element> is enabled",
            "Given <element> is not disabled"
    );

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForVerification(
                ElementShouldBeDisabledRule.SELENIUM_KEYWORD, aptStepTexts);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForVerification();
    }

    private static Stream<Arguments> relationWithIrrelevantWidgetTestCases() {
        return DataProvider.generateRelationStubsWithIrrelevantWidgetForVerification(
                ElementShouldBeDisabledRule.SELENIUM_KEYWORD);
    }

    private static Stream<Arguments> relationWithInaptStepTextTestCases() {
        return DataProvider.generateRelationStubsForVerification(
                ElementShouldBeDisabledRule.SELENIUM_KEYWORD, inaptStepTexts);
    }
}
