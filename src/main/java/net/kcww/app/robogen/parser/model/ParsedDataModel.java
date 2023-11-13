package net.kcww.app.robogen.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedDataModel {

    private GherkinModel gherkinModel;
    private List<XmlElementModel> xmlElements;
    private List<XsdElementModel> xsdElements;
}
