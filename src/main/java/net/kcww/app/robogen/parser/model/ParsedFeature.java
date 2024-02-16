package net.kcww.app.robogen.parser.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(fluent = true)
public class ParsedFeature {

    @NonNull
    private final String name;
    private final String description;
    private final ParsedScenario scenario;
}
