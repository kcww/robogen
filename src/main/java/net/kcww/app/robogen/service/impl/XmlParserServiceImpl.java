package net.kcww.app.robogen.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.kcww.app.robogen.model.XmlElementModel;
import net.kcww.app.robogen.service.impl.helper.XmlElements;
import net.kcww.app.robogen.service.impl.exception.XmlParsingException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.kcww.app.robogen.service.impl.helper.Elements.toStream;

@Service
@Slf4j
public class XmlParserServiceImpl extends AbstractParserService<XmlElementModel> {

  @Override
  public List<XmlElementModel> parse(InputStream inputStream) throws XmlParsingException {
    try {
      var document = buildDocument(inputStream);
      return extractElements(document);
    } catch (IOException | SAXException e) {
      throw new XmlParsingException("Failed to parse XML input stream", e);
    }
  }

  /**
   * Extracts XML elements from the document.
   *
   * @param document XML document
   * @return A list of XmlModels
   */
  private List<XmlElementModel> extractElements(Document document) {
    return streamElements(document)
      .filter(XmlElements::hasValidAttribute)
      .map(XmlElements::create)
//      .peek(model -> log.info("Extracted UI element: {}", model))
      .collect(Collectors.toList());
  }

  private Stream<Element> streamElements(Document document) {
    return toStream(document.getElementsByTagName(WILD_CARD));
  }
}
