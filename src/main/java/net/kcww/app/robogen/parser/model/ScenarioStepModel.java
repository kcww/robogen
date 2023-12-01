package net.kcww.app.robogen.parser.model;

import io.cucumber.messages.types.StepKeywordType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(fluent = true)
public class ScenarioStepModel implements Comparable<ScenarioStepModel> {

    // line number in the feature file
    private final long order;

    // e.g., When I enter <firstName> with "John"
    private StepKeywordType type;
    private final String text;
    private final List<String> parameters;
    private final List<String> literals;

    @Override
    public int compareTo(ScenarioStepModel other) {
        return Long.compare(this.order, other.order);
    }
}
