package net.kcww.app.robogen.mapper.service;

import io.cucumber.messages.types.GherkinDocument;
import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;

import java.util.List;

public interface MapperService {

  List<RelationModel> map(GherkinDocument gherkinDocument, List<XmlElementModel> xmlElementModels, List<XsdElementModel> xsdElementModels);
}
