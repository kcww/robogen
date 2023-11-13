package net.kcww.app.robogen.parser.model;

import io.cucumber.messages.types.GherkinDocument;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class GherkinModel {

    private final GherkinDocument gherkinDocument;
    private final String featureName;
    private final String featureDescription;
    private final String scenarioName;

    @Singular
    private final List<ScenarioStepModel> scenarioSteps;
}
