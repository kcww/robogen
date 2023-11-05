package net.kcww.app.robogen.service.impl;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.GherkinDocument;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.service.ParserService;
import net.kcww.app.robogen.service.impl.exception.GherkinParsingException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GherkinParserServiceImpl implements ParserService<GherkinDocument> {

  private final GherkinParser parser = GherkinParser.builder().build();

  @Override
  public List<GherkinDocument> parse(InputStream inputStream) throws GherkinParsingException {
    try {
      return parser.parse(Strings.EMPTY, inputStream)
        .map(Envelope::getGherkinDocument)
        .flatMap(Optional::stream)
//        .peek(document -> log.info("Parsed Gherkin document: {}", document))
        .collect(Collectors.toList());
    } catch (IOException e) {
      throw new GherkinParsingException("IOException occurred while parsing the Gherkin document", e);
    }
  }
}
