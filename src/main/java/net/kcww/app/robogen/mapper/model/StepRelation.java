package net.kcww.app.robogen.mapper.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.parser.model.ParsedStep;
import net.kcww.app.robogen.parser.model.XmlElement;
import net.kcww.app.robogen.parser.model.XsdElement;

@Data
@Builder
@Accessors(fluent = true)
public class StepRelation {

    @NonNull
    private final ParsedStep parsedStep;    // Parsed Scenario step
    private final XmlElement xmlElement;    // Parsed UI element
    private final XsdElement xsdElement;    // Parsed XSD element
}
