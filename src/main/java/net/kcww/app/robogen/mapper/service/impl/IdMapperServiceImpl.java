package net.kcww.app.robogen.mapper.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.TextMatcher;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.parser.model.ParsedDataModel;
import net.kcww.app.robogen.parser.model.ScenarioStepModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IdMapperServiceImpl implements MapperService<ParsedDataModel, List<RelationModel>> {

    @Override
    public List<RelationModel> map(ParsedDataModel parsedData) {
        return parsedData.gherkinModel().scenarioSteps().stream()
                .map(scenarioStep -> createRelationModel(scenarioStep, parsedData)).toList();
    }

    private RelationModel createRelationModel(ScenarioStepModel scenarioStep, ParsedDataModel parsedData) {
        var builder = RelationModel.builder().scenarioStep(scenarioStep);

        parsedData.xmlElements().stream()
                .filter(xmlElement -> isMatchFound(xmlElement, scenarioStep))
                .findFirst()
                .ifPresent(xmlElement -> {
                    builder.xmlElement(xmlElement);
                    matchXsdElement(builder, xmlElement, parsedData);
                });

        return builder.build();
    }

    private boolean isMatchFound(XmlElementModel xmlElement, ScenarioStepModel scenarioStep) {
        String xmlElementId = xmlElement.id();
        Optional<String> scenarioStepId = getScenarioStepIdCandidate(xmlElementId, scenarioStep);
        return scenarioStepId.isPresent() && scenarioStepId.get().equalsIgnoreCase(xmlElementId);
    }

    private Optional<String> getScenarioStepIdCandidate(String xmlElementId, ScenarioStepModel scenarioStep) {
        return scenarioStep.parameters().stream().findFirst().or(() ->
                TextMatcher.extractScenarioStepId(scenarioStep.text(), xmlElementId));
    }

    private void matchXsdElement(RelationModel.RelationModelBuilder builder,
                                 XmlElementModel xmlElement,
                                 ParsedDataModel parsedData) {
        parsedData.xsdElements().stream()
                .filter(xsdElement -> xsdElement.name().equalsIgnoreCase(xmlElement.id()))
                .findFirst()
                .ifPresent(builder::xsdElement);
    }
}
