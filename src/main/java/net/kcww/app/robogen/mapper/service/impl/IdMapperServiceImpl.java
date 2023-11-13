package net.kcww.app.robogen.mapper.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.KeyMatcher;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.parser.model.ParsedDataModel;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IdMapperServiceImpl implements MapperService<ParsedDataModel, List<RelationModel>> {

    /**
     * Maps Gherkin document steps to their corresponding XML and XSD elements.
     *
     * @param parsedData Parsed data containing Gherkin, XML, and XSD elements.
     * @return A list of RelationModels representing the mappings between Gherkin steps and XML/XSD elements.
     */
    @Override
    public List<RelationModel> map(ParsedDataModel parsedData) {
        return parsedData.getGherkinModel().getScenarioSteps().stream()
                .map(step -> createRelationModel(step, parsedData))
                .collect(Collectors.toList());
    }

    /**
     * Creates a RelationModel mapping a ScenarioStepModel to corresponding XML and XSD elements.
     *
     * @param scenarioStep Scenario scenarioStep model.
     * @param parsedData   Parsed data model.
     * @return A RelationModel representing the mapping.
     */
    private RelationModel createRelationModel(ScenarioStepModel scenarioStep, ParsedDataModel parsedData) {
        var builder = RelationModel.builder().scenarioStep(scenarioStep);

        parsedData.getXmlElements().stream()
                .filter(xmlElement -> matchesScenarioStep(xmlElement, scenarioStep))
                .findFirst()
                .ifPresent(xmlElement -> {
                    builder.xmlElement(xmlElement);
                    matchXsdElement(builder, xmlElement, parsedData);
                });

        return builder.build();
    }

    private boolean matchesScenarioStep(XmlElementModel xmlElement, ScenarioStepModel scenarioStep) {
        var id = scenarioStep.getId();
        if (id == null || id.isBlank()) return false;

        var normalizedId = KeyMatcher.normalize(id);
        return Optional.ofNullable(xmlElement.getId()).map(KeyMatcher::normalize).stream()
                .anyMatch(normalizedId::equalsIgnoreCase) ||
                Optional.ofNullable(xmlElement.getName()).map(KeyMatcher::normalize).stream()
                        .anyMatch(normalizedId::equalsIgnoreCase);
    }

    /**
     * Matches an XML element to a corresponding XSD element and updates the RelationModel builder.
     *
     * @param builder    RelationModel builder.
     * @param xmlElement XML element model.
     * @param parsedData Parsed data model.
     */
    private void matchXsdElement(RelationModel.RelationModelBuilder builder,
                                 XmlElementModel xmlElement,
                                 ParsedDataModel parsedData) {

        parsedData.getXsdElements().stream()
                .filter(xsdElement -> xsdElement.getName().equalsIgnoreCase(xmlElement.getId()) ||
                        xsdElement.getName().equalsIgnoreCase(xmlElement.getName()))
                .findFirst()
                .ifPresent(builder::xsdElement);
    }
}
