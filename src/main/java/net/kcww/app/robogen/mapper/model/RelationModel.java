package net.kcww.app.robogen.mapper.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;

@Data
@Builder
@Accessors(fluent = true)
public class RelationModel {

    private final ScenarioStepModel scenarioStep; // Parsed Scenario steps
    private final XmlElementModel xmlElement;     // Parsed UI elements
    private final XsdElementModel xsdElement;     // Parsed XSD elements
}
