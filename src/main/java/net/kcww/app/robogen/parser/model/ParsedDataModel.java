package net.kcww.app.robogen.parser.model;

import io.cucumber.messages.types.GherkinDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedDataModel {

  private GherkinDocument gherkinDocument;
  private List<XmlElementModel> xmlElements;
  private List<XsdElementModel> xsdElements;
}
