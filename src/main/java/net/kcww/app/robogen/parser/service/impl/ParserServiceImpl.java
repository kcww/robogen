package net.kcww.app.robogen.parser.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.parser.exception.ParsingException;
import net.kcww.app.robogen.parser.model.ParsedFeature;
import net.kcww.app.robogen.parser.model.ParsedUserInput;
import net.kcww.app.robogen.parser.model.XmlElement;
import net.kcww.app.robogen.parser.model.XsdElement;
import net.kcww.app.robogen.parser.service.ParserService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Service implementation for parsing multiple file types based on UserInput.
 */
@Service
@AllArgsConstructor
@Slf4j
public class ParserServiceImpl implements ParserService<UserInput, ParsedUserInput> {

    private static final String PARSING_ERROR_MSG = "Error occurred while parsing input files: ";
    private final ParserService<InputStream, ParsedFeature> featureParserService;
    private final ParserService<InputStream, List<XmlElement>> xmlParserService;
    private final ParserService<InputStream, List<XsdElement>> xsdParserService;

    @Override
    public ParsedUserInput parse(UserInput input) throws ParsingException {
        try {
            var feature = parseFeature(input.getFeatureFile());
            var xmlElements = parseXml(input.getXmlFile());
            var xsdElements = parseXsd(input.getXsdFile());
            return new ParsedUserInput(feature, xmlElements, xsdElements);
        } catch (Exception e) {
            String detailedError = PARSING_ERROR_MSG + e.getMessage();
            log.error(detailedError, e);
            throw new ParsingException(detailedError, e);
        }
    }

    private ParsedFeature parseFeature(byte[] featureFile) throws IOException, ParsingException {
        try (InputStream featureInputStream = new ByteArrayInputStream(featureFile)) {
            return featureParserService.parse(featureInputStream);
        }
    }

    private List<XmlElement> parseXml(byte[] xmlFile) throws IOException, ParsingException {
        try (InputStream xmlInputStream = new ByteArrayInputStream(xmlFile)) {
            return xmlParserService.parse(xmlInputStream);
        }
    }

    private List<XsdElement> parseXsd(byte[] xsdFile) throws IOException, ParsingException {
        try (InputStream xsdInputStream = new ByteArrayInputStream(xsdFile)) {
            return xsdParserService.parse(xsdInputStream);
        }
    }
}
