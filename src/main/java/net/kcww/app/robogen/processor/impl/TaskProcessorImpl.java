package net.kcww.app.robogen.processor.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.composer.model.ComposingMaterialModel;
import net.kcww.app.robogen.composer.service.ComposerService;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.parser.exception.ParsingException;
import net.kcww.app.robogen.parser.model.ParsedDataModel;
import net.kcww.app.robogen.parser.service.ParserService;
import net.kcww.app.robogen.processor.TaskProcessor;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.service.TranslatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskProcessorImpl implements TaskProcessor<UserInput> {

    private final ParserService<UserInput, ParsedDataModel> parserService;
    private final MapperService<ParsedDataModel, List<RelationModel>> mapperService;
    private final TranslatorService<RelationModel, KeywordModel> translatorService;
    private final ComposerService<ComposingMaterialModel, String> robotComposerService;

    @Override
    public void process(UserInput userInput) {
        var parsedData = parse(userInput);
        var relations = map(parsedData);
        var keywords = translate(relations);
        var script = compose(keywords);
        log.info("Generated Robot Framework script:\n{}", script);
    }

    private ParsedDataModel parse(UserInput userInput) {
        try {
            return parserService.parse(userInput);
        } catch (ParsingException e) {
            log.error("Error occurred while parsing the input files.", e);
        }
        return null;
    }

    private List<RelationModel> map(ParsedDataModel parsedData) {
        return mapperService.map(parsedData);
    }

    private List<KeywordModel> translate(List<RelationModel> models) {
        return translatorService.translate(models);
    }

    private String compose(List<KeywordModel> models) {
        var material = ComposingMaterialModel.builder()
                .url("http://www.example.com")
                .featureName("<feature-name>")
                .featureDescription("<feature-description>")
                .scenarioName("<scenario-name>")
                .keywordModels(models)
                .build();
        return robotComposerService.compose(material);
    }
}
