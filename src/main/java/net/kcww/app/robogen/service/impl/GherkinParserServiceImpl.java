package net.kcww.app.robogen.service.impl;

import io.cucumber.gherkin.GherkinParser;
import io.cucumber.messages.types.Envelope;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.service.ParserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GherkinParserServiceImpl implements ParserService<Envelope> {

  private GherkinParser parser;

  @Override
  public List<Envelope> parse(InputStream inputStream) throws IOException {
    return getParser()
      .parse("", inputStream)
      .filter(envelope -> envelope.getGherkinDocument().isPresent())
      .peek(envelope -> log.info("GherkinDocument: {}", envelope.getGherkinDocument()))
      .collect(Collectors.toList());
  }

  private synchronized GherkinParser getParser() {
    if (parser == null) {
      parser = GherkinParser.builder().build();
    }
    return parser;
  }

}