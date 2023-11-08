package net.kcww.app.robogen.parser.service.impl;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.Envelope;
import io.cucumber.messages.types.GherkinDocument;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.exception.GherkinParsingException;
import net.kcww.app.robogen.parser.service.ParserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@Slf4j
public class GherkinParserServiceImpl implements ParserService<GherkinDocument> {

  private final GherkinParser parser = GherkinParser.builder().build();

  @Override
  public GherkinDocument parse(InputStream inputStream) throws GherkinParsingException {
    try {
      return parser.parse(Strings.EMPTY, inputStream)
        .map(Envelope::getGherkinDocument)
        .flatMap(Optional::stream)
        .findFirst()
        .orElseThrow();
    } catch (IOException e) {
      throw new GherkinParsingException("IOException occurred while parsing the Gherkin document", e);
    }
  }
}
