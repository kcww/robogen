package net.kcww.app.robogen.processor.impl;

import io.cucumber.messages.types.GherkinDocument;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.composer.model.MaterialModel;
import net.kcww.app.robogen.composer.service.ComposerService;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.parser.exception.ParsingException;
import net.kcww.app.robogen.parser.model.ParsedDataModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import net.kcww.app.robogen.parser.service.ParserService;
import net.kcww.app.robogen.processor.TaskProcessor;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.service.TranslatorService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskProcessorImpl implements TaskProcessor<UserInput> {

  private final ParserService<GherkinDocument> gherkinParserService;
  private final ParserService<List<XmlElementModel>> xmlParserService;
  private final ParserService<List<XsdElementModel>> xsdParserService;
  private final MapperService mapperService;
  private final TranslatorService<RelationModel, KeywordModel> translatorService;
  private final ComposerService<MaterialModel, String> robotComposerService;

  @Override
  public void process(UserInput input) {
    var parsedData = parse(input);
    var relations = map(parsedData);
    var keywords = translate(relations);
    var script = compose(keywords);
    log.info("Generated Robot Framework script:\n{}", script);
//    var dataset = compose(...);
  }

  private ParsedDataModel parse(UserInput input) {
    try (var featureStream = new ByteArrayInputStream(input.getFeatureFile());
         var xmlStream = new ByteArrayInputStream(input.getXmlFile());
         var xsdStream = new ByteArrayInputStream(input.getXsdFile())) {
      var gherkinDocument = gherkinParserService.parse(featureStream);
      var xmlElements = xmlParserService.parse(xmlStream);
      var xsdElements = xsdParserService.parse(xsdStream);
      return new ParsedDataModel(gherkinDocument, xmlElements, xsdElements);
    } catch (ParsingException | IOException e) {
      log.error("Error occurred while parsing the input files.", e);
    }
    return null;
  }

  private List<RelationModel> map(ParsedDataModel model) {
    return mapperService.map(model.getGherkinDocument(), model.getXmlElements(), model.getXsdElements());
  }

  private List<KeywordModel> translate(List<RelationModel> models) {
    return translatorService.translate(models);
  }

  private String compose(List<KeywordModel> models) {
    var material = MaterialModel.builder()
      .url("http://www.example.com")
      .featureName("Feature Name")
      .featureDescription("Feature Description")
      .scenarioName("Scenario Name")
      .keywordModels(models)
      .build();
    return robotComposerService.compose(material);
  }
}
