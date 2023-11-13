package net.kcww.app.robogen.parser.model;

import io.cucumber.messages.types.StepKeywordType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScenarioStepModel implements Comparable<ScenarioStepModel> {

    private final long order;
    private StepKeywordType stepKeywordType;
    private final String text;
    private final String id;
//    private final String value;

    @Override
    public int compareTo(ScenarioStepModel other) {
        return Long.compare(this.order, other.order);
    }
}
