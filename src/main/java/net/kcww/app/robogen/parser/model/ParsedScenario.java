package net.kcww.app.robogen.parser.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(fluent = true)
public class ParsedScenario {

    @NonNull
    private final String name;
    private final List<ParsedStep> parsedSteps;
}
