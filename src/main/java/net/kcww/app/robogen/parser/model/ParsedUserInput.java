package net.kcww.app.robogen.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class ParsedUserInput {

    @NonNull
    private ParsedFeature parsedFeature;
    private List<XmlElement> xmlElements;
    private List<XsdElement> xsdElements;
}
