package net.kcww.app.robogen.mapper.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.common.helper.Texts;
import net.kcww.app.robogen.mapper.model.StepRelation;
import net.kcww.app.robogen.mapper.service.MapperService;
import net.kcww.app.robogen.parser.model.ParsedStep;
import net.kcww.app.robogen.parser.model.ParsedUserInput;
import net.kcww.app.robogen.parser.model.XmlElement;
import net.kcww.app.robogen.parser.model.XsdElement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IdMapperServiceImpl implements MapperService<ParsedUserInput, List<StepRelation>> {

    @Override
    public List<StepRelation> map(ParsedUserInput input) {
        return input.parsedFeature().scenario().parsedSteps().stream()
                .map(step -> buildStepRelation(input, step))
                .toList();
    }

    private StepRelation buildStepRelation(ParsedUserInput input, ParsedStep step) {
        var builder = StepRelation.builder().parsedStep(step);

        findMatchingXmlElement(step, input.xmlElements()).ifPresent(xmlElement -> {
            builder.xmlElement(xmlElement);
            findMatchingXsdElement(xmlElement, input.xsdElements()).ifPresent(builder::xsdElement);
        });

        return builder.build();
    }

    private Optional<XmlElement> findMatchingXmlElement(ParsedStep step, List<XmlElement> xmlElements) {
        return xmlElements.stream()
                .filter(xmlElement -> Texts.containsWord(step.text(), xmlElement.id()))
                .findFirst();
    }

    private Optional<XsdElement> findMatchingXsdElement(XmlElement xmlElement, List<XsdElement> xsdElements) {
        return xsdElements.stream()
                .filter(xsdElement -> xsdElement.name().equalsIgnoreCase(xmlElement.id()))
                .findFirst();
    }
}
