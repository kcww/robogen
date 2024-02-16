package net.kcww.app.robogen.parser.service.impl;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.*;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.GherkinParsingException;
import net.kcww.app.robogen.parser.model.ParsedFeature;
import net.kcww.app.robogen.parser.model.ParsedScenario;
import net.kcww.app.robogen.parser.model.ParsedStep;
import net.kcww.app.robogen.parser.service.ParserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FeatureParserServiceImpl implements ParserService<InputStream, ParsedFeature> {

    private static final String PARSING_ERROR_MSG = "IOException occurred while parsing Gherkin document: ";
    private final GherkinParser parser = GherkinParser.builder().build();

    @Override
    public ParsedFeature parse(InputStream inputStream) throws GherkinParsingException {
        try (Stream<Envelope> envelopes = parser.parse("", inputStream)) {
            return envelopes
                    .flatMap(envelope -> envelope.getGherkinDocument().stream())
                    .flatMap(document -> document.getFeature().stream())
                    .findFirst()
                    .map(this::buildFeature)
                    .orElseThrow(() -> new GherkinParsingException("No feature found in Gherkin document"));
        } catch (IOException e) {
            String detailedError = PARSING_ERROR_MSG + e.getMessage();
            log.error(detailedError, e);
            throw new GherkinParsingException(detailedError, e);
        }
    }

    private ParsedFeature buildFeature(Feature feature) {
        var builder = ParsedFeature.builder()
                .name(feature.getName().trim())
                .description(Optional.ofNullable(feature.getDescription()).map(String::trim).orElse(""));

        // The current requirement, only the first scenario is parsed.
        feature.getChildren().stream()
                .map(FeatureChild::getScenario)
                .flatMap(Optional::stream)
                .findFirst()
                .ifPresent(scenario -> builder.scenario(buildScenario(scenario)));

        return builder.build();
    }

    private ParsedScenario buildScenario(Scenario scenario) {
        var sortedSteps = scenario.getSteps().stream()
                .map(this::buildScenarioStep)
                .sorted()
                .collect(Collectors.toList());

        var finalSteps = assignConjunctionStepTypes(sortedSteps);

        return ParsedScenario.builder()
                .name(scenario.getName().trim())
                .parsedSteps(finalSteps)
                .build();
    }

    private ParsedStep buildScenarioStep(Step step) {
        return ParsedStep.builder()
                .order(step.getLocation().getLine())
                .text(step.getText())
                .type(determineStepType(step.getKeywordType()))
                .build();
    }

    private ParsedStep.Type determineStepType(Optional<StepKeywordType> keywordType) {
        return keywordType.map(type -> switch (type) {
            case CONTEXT -> ParsedStep.Type.GIVEN;
            case ACTION -> ParsedStep.Type.WHEN;
            case OUTCOME -> ParsedStep.Type.THEN;
            case CONJUNCTION -> ParsedStep.Type.AND;
            default -> ParsedStep.Type.UNKNOWN;
        }).orElse(ParsedStep.Type.UNKNOWN);
    }

    private List<ParsedStep> assignConjunctionStepTypes(List<ParsedStep> steps) {
        if (steps.isEmpty()) return steps;

        var resultSteps = new ArrayList<ParsedStep>(steps.size());
        resultSteps.add(steps.get(0));

        for (int i = 1; i < steps.size(); i++) {
            var currentStep = steps.get(i);
            var previousStep = resultSteps.get(i - 1);

            if (currentStep.type() == ParsedStep.Type.AND) {
                ParsedStep newStep = ParsedStep.builder()
                        .order(currentStep.order())
                        .text(currentStep.text())
                        .type(previousStep.type())
                        .build();
                resultSteps.add(newStep);
            } else {
                resultSteps.add(currentStep);
            }
        }

        return Collections.unmodifiableList(resultSteps);
    }
}
