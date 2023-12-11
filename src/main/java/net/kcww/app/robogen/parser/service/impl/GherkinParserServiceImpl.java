package net.kcww.app.robogen.parser.service.impl;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.*;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.parser.exception.GherkinParsingException;
import net.kcww.app.robogen.parser.model.GherkinModel;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.service.ParserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for parsing Gherkin files. This service processes
 * Gherkin input streams and converts them into GherkinModel objects.
 */
@Service
@Slf4j
public class GherkinParserServiceImpl implements ParserService<InputStream, GherkinModel> {

    private final GherkinParser parser = GherkinParser.builder().build();

    /**
     * Parses an InputStream of a Gherkin file into a GherkinModel.
     *
     * @param inputStream the input stream of the Gherkin file.
     * @return a GherkinModel representing the parsed Gherkin document.
     * @throws GherkinParsingException if parsing fails due to an IO exception.
     */
    @Override
    public GherkinModel parse(InputStream inputStream) throws GherkinParsingException {
        try {
            return parseGherkinDocument(inputStream)
                    .orElseThrow(() -> new GherkinParsingException("Failed to parse Gherkin document"));
        } catch (IOException e) {
            log.error("IOException occurred while parsing the Gherkin document", e);
            throw new GherkinParsingException("IOException occurred while parsing the Gherkin document", e);
        }
    }

    private Optional<GherkinModel> parseGherkinDocument(InputStream inputStream) throws IOException {
        return parser.parse("", inputStream)
                .map(Envelope::getGherkinDocument)
                .flatMap(Optional::stream)
                .findFirst()
                .map(this::buildGherkinModel);
    }

    private GherkinModel buildGherkinModel(GherkinDocument doc) {
        var builder = GherkinModel.builder().document(doc);

        doc.getFeature().ifPresent(feature -> {
            builder.featureName(feature.getName())
                    .featureDescription(feature.getDescription());

            feature.getChildren().stream()
                    .map(FeatureChild::getScenario)
                    .flatMap(Optional::stream)
                    .findFirst()
                    .ifPresent(scenario -> {
                        builder.scenarioName(scenario.getName())
                                .scenarioSteps(processScenarioSteps(scenario));
                    });
        });
        return builder.build();
    }

    private List<ScenarioStepModel> processScenarioSteps(Scenario scenario) {
        var steps = scenario.getSteps().stream()
                .map(this::buildScenarioStepModel)
                .toList();
        return changeStepConjunctionTypeToItsPredecessorType(steps);
    }

    private ScenarioStepModel buildScenarioStepModel(Step step) {
        var builder = ScenarioStepModel.builder().order(step.getLocation().getLine());
        step.getKeywordType().ifPresent(builder::type);

        var text = step.getText();
        builder.text(text);
        builder.parameters(TextMatcher.extractScenarioStepParameters(text));
        builder.literals(TextMatcher.extractScenarioStepLiterals(text));

        return builder.build();
    }

    private List<ScenarioStepModel> changeStepConjunctionTypeToItsPredecessorType(List<ScenarioStepModel> steps) {
        if (steps.isEmpty() || steps.size() == 1) return steps;

        StepKeywordType lastKeywordType = null;
        for (var step : steps) {
            var currentKeywordType = step.type();
            if (StepKeywordType.CONJUNCTION != currentKeywordType) {
                lastKeywordType = currentKeywordType;
            } else if (lastKeywordType != null) {
                step.type(lastKeywordType);
            }
        }
        return steps;
    }
}
