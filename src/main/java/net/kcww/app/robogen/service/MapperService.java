package net.kcww.app.robogen.service;

import io.cucumber.messages.types.GherkinDocument;
import net.kcww.app.robogen.model.RelationModel;
import net.kcww.app.robogen.model.XmlElementModel;
import net.kcww.app.robogen.model.XsdElementModel;

import java.util.List;

public interface MapperService {

  List<RelationModel> map(GherkinDocument gherkinDocument, List<XmlElementModel> xmlElementModels);

  List<RelationModel> map(GherkinDocument gherkinDocument, List<XmlElementModel> xmlElementModels, List<XsdElementModel> xsdElementModels);
}
