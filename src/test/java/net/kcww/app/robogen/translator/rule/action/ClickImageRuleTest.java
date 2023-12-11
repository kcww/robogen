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

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ClickImageRuleTest extends AbstractElementRuleTest {

    @InjectMocks
    private ClickImageRule rule;

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

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForAction(ClickImageRule.KEYWORD);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForAction();
    }

    private static Stream<Arguments> relationWithIrrelevantWidgetTestCases() {
        return DataProvider.generateRelationStubsWithIrrelevantWidgetForAction(ClickImageRule.KEYWORD);
    }
}
