package net.kcww.app.robogen.mapper.model;

import io.cucumber.messages.types.Step;
import lombok.Builder;
import lombok.Data;
import net.kcww.app.robogen.parser.model.XmlElementModel;
import net.kcww.app.robogen.parser.model.XsdElementModel;

@Data
@Builder
public class RelationModel {

  private Step step;
  private XmlElementModel xmlElement;
  private XsdElementModel xsdElement;
}
