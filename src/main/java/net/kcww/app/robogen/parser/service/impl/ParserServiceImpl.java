package net.kcww.app.robogen.parser.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.parser.exception.ParsingException;
import net.kcww.app.robogen.parser.model.GherkinModel;
import net.kcww.app.robogen.parser.model.ParsedDataModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;
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
public class ParserServiceImpl implements ParserService<UserInput, ParsedDataModel> {

    private final ParserService<InputStream, GherkinModel> gherkinParserService;
    private final ParserService<InputStream, List<XmlElementModel>> xmlParserService;
    private final ParserService<InputStream, List<XsdElementModel>> xsdParserService;

    /**
     * Parses the contents of UserInput and constructs a ParsedDataModel.
     * This method orchestrates the parsing of different types of files provided in the UserInput.
     *
     * @param input the UserInput containing the files to be parsed. It includes Gherkin, XML, and XSD files.
     * @return ParsedDataModel containing the parsed data from the input files.
     * @throws ParsingException if an error occurs during the parsing process. This can be due to file format issues,
     *                          IO problems, or other parsing-related errors. The exception encapsulates
     *                          the detailed cause of the failure.
     */
    @Override
    public ParsedDataModel parse(UserInput input) throws ParsingException {
        try {
            return parseInputFiles(input);
        } catch (IOException e) {
            log.error("IO Error occurred while parsing the input files.", e);
            throw new ParsingException("IO Error during parsing", e);
        } catch (ParsingException e) {
            log.error("Error occurred while parsing the input files.", e);
            throw e;
        }
    }

    /**
     * Parses the input files from UserInput into their respective models.
     *
     * @param input the UserInput containing the files.
     * @return ParsedDataModel containing the parsed data.
     * @throws ParsingException if an error occurs during parsing.
     * @throws IOException      if an IO error occurs during file processing.
     */
    private ParsedDataModel parseInputFiles(UserInput input) throws ParsingException, IOException {
        try (var featureStream = new ByteArrayInputStream(input.getFeatureFile());
             var xmlStream = new ByteArrayInputStream(input.getXmlFile());
             var xsdStream = new ByteArrayInputStream(input.getXsdFile())) {

            var gherkinDocument = gherkinParserService.parse(featureStream);
            var xmlElements = xmlParserService.parse(xmlStream);
            var xsdElements = xsdParserService.parse(xsdStream);

            return new ParsedDataModel(gherkinDocument, xmlElements, xsdElements);
        }
    }
}
