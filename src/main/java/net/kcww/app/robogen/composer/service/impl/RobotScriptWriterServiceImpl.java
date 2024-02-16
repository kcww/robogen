package net.kcww.app.robogen.composer.service.impl;

import lombok.AllArgsConstructor;
import net.kcww.app.robogen.common.helper.Texts;
import net.kcww.app.robogen.composer.helper.RobotScripts;
import net.kcww.app.robogen.composer.model.RobotScript;
import net.kcww.app.robogen.composer.service.ScriptWriterService;
import net.kcww.app.robogen.composer.service.impl.robot.KeywordSectionWriter;
import net.kcww.app.robogen.composer.service.impl.robot.SettingSectionWriter;
import net.kcww.app.robogen.composer.service.impl.robot.TestcaseSectionWriter;
import net.kcww.app.robogen.composer.service.impl.robot.VariableSectionWriter;
import net.kcww.app.robogen.parser.model.ParsedFeature;
import net.kcww.app.robogen.translator.model.Keyword;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.kcww.app.robogen.composer.helper.RobotScripts.NEW_LINE;

@Service
@AllArgsConstructor
public class RobotScriptWriterServiceImpl implements ScriptWriterService<ParsedFeature, List<Keyword>, String> {

    @Override
    public String compose(ParsedFeature parsedFeature, List<Keyword> keywords) {
        var robotScript = RobotScript.builder()
                .url(RobotScripts.determineUrl(parsedFeature, keywords))
                .featureName(parsedFeature.name())
                .featureDescription(parsedFeature.description())
                .scenarioName(Texts.capitalize(parsedFeature.scenario().name()))
                .keywords(keywords)
                .build();
        return String.join(NEW_LINE,
                new SettingSectionWriter().write(robotScript),
                new VariableSectionWriter().write(robotScript),
                new TestcaseSectionWriter().write(robotScript),
                new KeywordSectionWriter().write(robotScript));
    }
}
