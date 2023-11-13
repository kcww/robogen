package net.kcww.app.robogen.mapper.model;

import lombok.Builder;
import lombok.Data;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;

@Data
@Builder
public class RelationModel {

    private ScenarioStepModel scenarioStep; // Parsed scenario steps
    private XmlElementModel xmlElement;     // Parsed UI elements
    private XsdElementModel xsdElement;     // Parsed XSD elements
}
