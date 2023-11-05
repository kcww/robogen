package net.kcww.app.robogen.model;

import io.cucumber.messages.types.Step;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class RelationModel {

  @NonNull
  private Step step;
  private XmlElementModel xmlElement;
  private XsdElementModel xsdElement;
}
