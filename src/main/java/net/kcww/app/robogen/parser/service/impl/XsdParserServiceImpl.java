package net.kcww.app.robogen.parser.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import net.kcww.app.robogen.parser.exception.XsdParsingException;
import net.kcww.app.robogen.parser.helper.XsdElements;
import net.kcww.app.robogen.parser.helper.XsdSimpleTypes;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.kcww.app.robogen.parser.model.XsdElementEnum.ELEMENT;
import static net.kcww.app.robogen.parser.helper.Elements.toStream;

@Service
@Slf4j
public class XsdParserServiceImpl extends AbstractParserService<List<XsdElementModel>> {

  @Override
  public List<XsdElementModel> parse(InputStream inputStream) throws XsdParsingException {
    try {
      var document = buildDocument(inputStream);
      return extractElements(document);
    } catch (IOException | SAXException e) {
      throw new XsdParsingException("Failed to parse XSD input stream", e);
    }
  }

  /**
   * Extracts XsdModels from the given element.
   *
   * @param document XSD document
   * @return A list of XsdModels
   */
  private List<XsdElementModel> extractElements(Document document) {
    return streamElements(document)
      .filter(XsdSimpleTypes::hasSimpleTypeAsDirectChild)
      .map(XsdElements::create)
//      .peek(model -> log.info("Extracted XSD element: {}", model))
      .collect(Collectors.toList());
  }

  private Stream<Element> streamElements(Document document) {
    return toStream(document.getElementsByTagNameNS(WILD_CARD, ELEMENT.getName()));
  }
}
