package net.kcww.app.robogen.translator.helper;

import io.cucumber.messages.types.StepKeywordType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.common.helper.Texts;
import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.Widget;
import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.cucumber.messages.types.StepKeywordType.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataProvider {

    // Elicitable Relation Cases:
    private static final EnumSet<StepKeywordType> ACTION_STEP_TYPE = EnumSet.of(ACTION);
    private static final EnumSet<StepKeywordType> VERIFICATION_STEP_TYPES = EnumSet.of(CONTEXT, OUTCOME);

    public static Stream<Arguments> generateRelationStubsForAction(SeleniumKeyword seleniumKeyword) {
        return generateRelationStubs(ACTION_STEP_TYPE, null, Widgets.getRelevantWidgets(seleniumKeyword), false);
    }

    public static Stream<Arguments> generateRelationStubsForAction(SeleniumKeyword seleniumKeyword, Set<String> stepTexts) {
        return generateRelationStubs(ACTION_STEP_TYPE, stepTexts, Widgets.getRelevantWidgets(seleniumKeyword), false);
    }

    public static Stream<Arguments> generateRelationStubsForVerification(Set<String> stepTexts) {
        return generateRelationStubs(VERIFICATION_STEP_TYPES, stepTexts, null, false);
    }

    public static Stream<Arguments> generateRelationStubsWithStepParametersForVerification(Set<String> stepTexts) {
        return generateRelationStubs(VERIFICATION_STEP_TYPES, stepTexts, null, true);
    }

    public static Stream<Arguments> generateRelationStubsForVerification(SeleniumKeyword seleniumKeyword) {
        return generateRelationStubs(VERIFICATION_STEP_TYPES, null, Widgets.getRelevantWidgets(seleniumKeyword), false);
    }

    public static Stream<Arguments> generateRelationStubsForVerification(SeleniumKeyword seleniumKeyword, Set<String> stepTexts) {
        return generateRelationStubs(VERIFICATION_STEP_TYPES, stepTexts, Widgets.getRelevantWidgets(seleniumKeyword), false);
    }

    // Any Widgets with inapt Step Type Cases:
    private static final EnumSet<StepKeywordType> ACTION_STEP_TYPE_COMPLEMENT = EnumSet.complementOf(ACTION_STEP_TYPE);
    private static final EnumSet<StepKeywordType> VERIFICATION_STEP_TYPES_COMPLEMENT = EnumSet.complementOf(VERIFICATION_STEP_TYPES);

    public static Stream<Arguments> generateRelationStubsWithInaptStepTypeForAction() {
        return generateRelationStubs(ACTION_STEP_TYPE_COMPLEMENT, null, null, false);
    }

    public static Stream<Arguments> generateRelationStubsWithInaptStepTypeForVerification() {
        return generateRelationStubs(VERIFICATION_STEP_TYPES_COMPLEMENT, null, null, false);
    }

    // Irrelevant Widget Cases:
    public static Stream<Arguments> generateRelationStubsWithIrrelevantWidgetForAction(SeleniumKeyword seleniumKeyword) {
        return generateRelationStubs(ACTION_STEP_TYPE, null, getIrrelevantWidgets(seleniumKeyword), false);
    }

    public static Stream<Arguments> generateRelationStubsWithIrrelevantWidgetForVerification(SeleniumKeyword seleniumKeyword) {
        return generateRelationStubs(VERIFICATION_STEP_TYPES, null, getIrrelevantWidgets(seleniumKeyword), false);
    }

    public static Stream<Arguments> generateRelationStubsWithRandomWidgetForVerification(int count) {
        return generateRelationStubs(VERIFICATION_STEP_TYPES, null, getRandomWidgets(count), false);
    }

    private static Stream<Arguments> generateRelationStubs(EnumSet<StepKeywordType> stepTypes,
                                                           Set<String> stepTexts,
                                                           EnumSet<WidgetEnum> widgets,
                                                           boolean isStepParameter) {
        return stepTypes.stream()
                .flatMap(stepType -> combineStreams(
                        createWidgetStream(widgets),
                        () -> createStepTextStream(stepTexts),
                        (widget, stepText) -> buildRelationStub(stepType, stepText, widget, isStepParameter)));
    }

    private static <T, U, R> Stream<R> combineStreams(Stream<T> stream1, Supplier<Stream<U>> stream2Supplier, BiFunction<T, U, R> combiner) {
        return stream1.flatMap(item1 -> stream2Supplier.get().map(item2 -> combiner.apply(item1, item2)));
    }

    private static Stream<WidgetEnum> createWidgetStream(EnumSet<WidgetEnum> widgets) {
        return widgets != null ? widgets.stream() : Stream.of((WidgetEnum) null);
    }

    private static Stream<String> createStepTextStream(Set<String> stepTexts) {
        return stepTexts != null ? stepTexts.stream() : Stream.of((String) null);
    }

    private static Arguments buildRelationStub(StepKeywordType stepType, String stepText, Widget widget, boolean extractStepParameters) {
        var stepParameters = extractStepParameters && stepText != null ? Texts.(stepText) : null;
        var tagName = widget != null ? widget.tagName() : null;
        return Arguments.of(new RelationModelStub(stepType, stepText, stepParameters, tagName));
    }

    private static EnumSet<WidgetEnum> getIrrelevantWidgets(SeleniumKeyword seleniumKeyword) {
        var relevantWidgets = Widgets.getRelevantWidgets(seleniumKeyword);
        var allWidgets = EnumSet.allOf(WidgetEnum.class);
        allWidgets.removeAll(relevantWidgets);
        return allWidgets;
    }

    private static EnumSet<WidgetEnum> getRandomWidgets(int count) {
        List<WidgetEnum> shuffledWidgets = new ArrayList<>(EnumSet.allOf(WidgetEnum.class));
        Collections.shuffle(shuffledWidgets);
        return shuffledWidgets.stream().limit(count).collect(Collectors.toCollection(() -> EnumSet.noneOf(WidgetEnum.class)));
    }
}
