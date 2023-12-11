package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.helper.DataProvider;
import net.kcww.app.robogen.translator.helper.RelationModelStub;
import net.kcww.app.robogen.translator.rule.AbstractNonElementRuleTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class TitleShouldBeRuleTest extends AbstractNonElementRuleTest {

    @InjectMocks
    private TitleShouldBeRule rule;

    @ParameterizedTest
    @MethodSource("elicitableRelationTestCases")
    public void elicitableRelationTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, true);
    }

    @ParameterizedTest
    @MethodSource("relationWithRandomWidgetTestCases")
    public void relationWithWidgetTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    @ParameterizedTest
    @MethodSource("relationWithInaptStepTypeTestCases")
    public void relationWithInaptStepTypeTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    @ParameterizedTest
    @MethodSource("relationWithInaptStepTextTestCases")
    public void relationWithInaptStepTextTest(RelationModelStub relationStub) {
        runTest(rule, relationStub, false);
    }

    private static final Set<String> aptStepTexts = Set.of(
            "Given the title is \"Hotel Booking\""
    );

    private static final Set<String> inaptStepTexts = Set.of(
            "Given I am on http://localhost:8080/room-booking",
            "Given I am on http://www.example.com",
            "Given I am on https://www.example.com",
            "Given I am on www.example.com",
            "Then wait until the URL is http://localhost:8080/room-booking",
            "Then wait until the URL is http://www.example.com",
            "Then wait until the URL is https://www.example.com",
            "Then wait until the URL is www.example.com",
            "Given the page contains \"Hotel Booking\" header",
            "Then wait until the page contains \"Hotel Booking\" header",
            "Then wait until the title is \"Hotel Booking\""
    );

    private static Stream<Arguments> elicitableRelationTestCases() {
        return DataProvider.generateRelationStubsForVerification(aptStepTexts);
    }

    private static Stream<Arguments> relationWithInaptStepTypeTestCases() {
        return DataProvider.generateRelationStubsWithInaptStepTypeForVerification();
    }

    private static Stream<Arguments> relationWithRandomWidgetTestCases() {
        return DataProvider.generateRelationStubsWithRandomWidgetForVerification(3);
    }

    private static Stream<Arguments> relationWithInaptStepTextTestCases() {
        return DataProvider.generateRelationStubsForVerification(inaptStepTexts);
    }
}