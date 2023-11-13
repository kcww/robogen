package net.kcww.app.robogen.parser.service.impl;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.*;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.KeyMatcher;
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
            return parseGherkinDocument(inputStream).orElseThrow(() ->
                    new GherkinParsingException("Failed to parse Gherkin document"));
        } catch (IOException e) {
            log.error("IOException occurred while parsing the Gherkin document", e);
            throw new GherkinParsingException("IOException occurred while parsing the Gherkin document", e);
        }
    }

    /**
     * Parses the Gherkin document from an InputStream and converts it to a GherkinModel.
     *
     * @param inputStream the input stream of the Gherkin file.
     * @return an Optional containing a GherkinModel if parsing is successful.
     * @throws IOException if an IO error occurs during parsing.
     */
    private Optional<GherkinModel> parseGherkinDocument(InputStream inputStream) throws IOException {
        return parser.parse("", inputStream)
                .map(Envelope::getGherkinDocument)
                .flatMap(Optional::stream)
                .findFirst()
                .map(this::buildGherkinModel);
    }

    /**
     * Builds the GherkinModel from a GherkinDocument.
     *
     * @param doc the GherkinDocument object.
     * @return a GherkinModel built from the GherkinDocument.
     */
    private GherkinModel buildGherkinModel(GherkinDocument doc) {
        var builder = GherkinModel.builder().gherkinDocument(doc);
        doc.getFeature().ifPresent(feature -> {
            builder.featureName(feature.getName());
            builder.featureDescription(feature.getDescription());
            feature.getChildren().stream()
                    .map(FeatureChild::getScenario)
                    .flatMap(Optional::stream)
                    .findFirst()
                    .ifPresent(scenario -> {
                        builder.scenarioName(scenario.getName());

                    });
        });
        var model = builder.build();
//        correctKeywordTypes(model);
        return model;
    }

    private List<ScenarioStepModel> processScenario(Scenario scenario) {
        var steps =scenario.getSteps().stream()
                .map(this::buildScenarioStepModel)
                .sorted(ScenarioStepModel::compareTo)
                .toList();

        return changeConjunctionTypeToItsPredecessorType(steps);
    }

    private ScenarioStepModel buildScenarioStepModel(Step step) {
        var builder = ScenarioStepModel.builder().order(step.getLocation().getLine());

        step.getKeywordType().ifPresent(builder::stepKeywordType);

        var text = step.getText();
        if (!text.isBlank()) {
            builder.text(text);
            builder.id(KeyMatcher.extractXmlElementIdFromScenarioStepText(text));
//            builder.value(KeyMatcher.extractValueFromScenarioStepText(text));
        }
        return builder.build();
    }

    private List<ScenarioStepModel> changeConjunctionTypeToItsPredecessorType(List<ScenarioStepModel> steps) {
        if (steps.isEmpty() || steps.size() == 1) return steps;

        StepKeywordType lastKeywordType = null;
        for (var step : steps) {
            var currentKeywordType = step.getStepKeywordType();
            if (StepKeywordType.CONJUNCTION != currentKeywordType) {
                lastKeywordType = currentKeywordType;
            } else if (lastKeywordType != null) {
                step.setStepKeywordType(lastKeywordType);
            }
        }
        return steps;
    }
}
