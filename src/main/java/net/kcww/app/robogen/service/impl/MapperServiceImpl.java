package net.kcww.app.robogen.service.impl;

import io.cucumber.messages.types.GherkinDocument;
import io.cucumber.messages.types.Scenario;
import io.cucumber.messages.types.Step;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.model.RelationModel;
import net.kcww.app.robogen.model.XmlElementModel;
import net.kcww.app.robogen.model.XsdElementModel;
import net.kcww.app.robogen.service.MapperService;
import net.kcww.app.robogen.service.impl.helper.KeywordMatcher;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MapperServiceImpl implements MapperService {

  @Override
  public List<RelationModel> map(GherkinDocument gherkinDocument, List<XmlElementModel> xmlElementModels) {
    return map(gherkinDocument, xmlElementModels, List.of());
  }

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

  private RelationModel createRelationModel(Step step,
                                            List<XmlElementModel> xmlElements,
                                            List<XsdElementModel> xsdElements) {
    var builder = RelationModel.builder().step(step);

    xmlElements.stream()
      .filter(xmlElement -> matchesStepText(xmlElement, step.getText()))
      .findFirst()
      .ifPresent(xmlElement -> {
        builder.xmlElement(xmlElement);
        matchXsdElement(builder, xmlElement, xsdElements);
      });

    RelationModel relation = builder.build();
    log.info("Created relation for step '{}': {} : {}",
      step.getText(), relation.getXmlElement(), relation.getXsdElement());
    return relation;
  }

  private boolean matchesStepText(XmlElementModel xmlElement, String stepText) {
    return Optional.ofNullable(xmlElement.getId())
      .map(id -> KeywordMatcher.containsKeyword(id, stepText))
      .orElse(false) ||
      Optional.ofNullable(xmlElement.getName())
        .map(name -> KeywordMatcher.containsKeyword(name, stepText))
        .orElse(false);
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
