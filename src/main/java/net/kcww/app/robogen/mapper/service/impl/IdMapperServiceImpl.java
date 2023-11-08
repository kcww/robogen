package net.kcww.app.robogen.mapper.service.impl;

import io.cucumber.messages.types.GherkinDocument;
import io.cucumber.messages.types.Scenario;
import io.cucumber.messages.types.Step;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.mapper.helper.KeyMatcher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IdMapperServiceImpl implements MapperService {

  /**
   * Maps Gherkin document steps to their corresponding XML and XSD elements.
   *
   * @param gherkinDocument The Gherkin document to map.
   * @param xmlElements     The list of XML elements to match against.
   * @param xsdElements     The list of XSD elements to match against.
   * @return A list of relation models representing the mappings.
   */
  @Override
  public List<RelationModel> map(GherkinDocument gherkinDocument,
                                 List<XmlElementModel> xmlElements,
                                 List<XsdElementModel> xsdElements) {
    return getSteps(gherkinDocument).stream()
      .map(step -> createRelationModel(step, xmlElements, xsdElements))
      .collect(Collectors.toList());
  }

  private List<Step> getSteps(GherkinDocument gherkinDocument) {
    return gherkinDocument.getFeature()
      .flatMap(feature -> feature.getChildren().stream().findFirst())
      .flatMap(featureChild -> featureChild.getScenario().map(Scenario::getSteps))
      .orElse(Collections.emptyList());
  }

  private RelationModel createRelationModel(Step scenarioStep,
                                            List<XmlElementModel> xmlElements,
                                            List<XsdElementModel> xsdElements) {
    var builder = RelationModel.builder().step(scenarioStep);

    xmlElements.stream()
      .filter(xmlElement -> matchesScenarioStep(xmlElement, scenarioStep.getText()))
      .findFirst()
      .ifPresent(xmlElement -> {
        builder.xmlElement(xmlElement);
        matchXsdElement(builder, xmlElement, xsdElements);
      });

    RelationModel relation = builder.build();
    log.info("Relation: {} \n\t {} \n\t {}",
      scenarioStep.getText(), relation.getXmlElement(), relation.getXsdElement());
    return relation;
  }

  private boolean matchesScenarioStep(XmlElementModel element, String stepText) {
    return Optional.ofNullable(element.getId()).map(id -> KeyMatcher.contains(id, stepText)).orElse(false) ||
      Optional.ofNullable(element.getName()).map(name -> KeyMatcher.contains(name, stepText)).orElse(false);
  }

  private void matchXsdElement(RelationModel.RelationModelBuilder builder,
                               XmlElementModel xmlElement,
                               List<XsdElementModel> xsdElements) {
    xsdElements.stream()
      .filter(xsdElement -> xsdElement.getName().equalsIgnoreCase(xmlElement.getId()) ||
        xsdElement.getName().equalsIgnoreCase(xmlElement.getName()))
      .findFirst()
      .ifPresent(builder::xsdElement);
  }
}
