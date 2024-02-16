package net.kcww.app.robogen.processor.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.composer.service.ScriptWriterService;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.parser.exception.ParsingException;
import net.kcww.app.robogen.parser.model.ParsedFeature;
import net.kcww.app.robogen.parser.model.ParsedUserInput;
import net.kcww.app.robogen.parser.service.ParserService;
import net.kcww.app.robogen.processor.TaskProcessor;
import net.kcww.app.robogen.translator.model.Keyword;
import net.kcww.app.robogen.translator.service.TranslatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskProcessorImpl implements TaskProcessor<UserInput> {

    private final ParserService<UserInput, ParsedUserInput> parserService;
    private final MapperService<ParsedUserInput, List<StepRelation>> mapperService;
    private final TranslatorService<StepRelation, Keyword> translatorService;
    private final ScriptWriterService<ParsedFeature, List<Keyword>, String> scriptWriterService;

    @Override
    public void process(UserInput input) {
        try {
            ParsedUserInput parsedInput = parseInput(input);
//            log.info("Parsed input:\n{}", parsedInput);
            List<StepRelation> relations = mapToRelations(parsedInput);
//            log.info("Mapped relations:\n{}", relations);
            List<Keyword> keywords = translateToKeywords(relations);
            String script = composeScript(parsedInput.parsedFeature(), keywords);
            log.info("Generated Robot Framework script:\n{}", script);
        } catch (Exception e) {
            log.error("Error occurred during task processing", e);
        }
    }

    private ParsedUserInput parseInput(UserInput input) throws ParsingException {
        return parserService.parse(input);
    }

    private List<StepRelation> mapToRelations(ParsedUserInput parsedInput) {
        return mapperService.map(parsedInput);
    }

    private List<Keyword> translateToKeywords(List<StepRelation> relations) {
        return translatorService.translate(relations);
    }

    private String composeScript(ParsedFeature feature, List<Keyword> keywords) {
        return scriptWriterService.compose(feature, keywords);
    }
}
