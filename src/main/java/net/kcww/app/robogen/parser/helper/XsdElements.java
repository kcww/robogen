package net.kcww.app.robogen.parser.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.parser.model.XsdElementModel;
import org.w3c.dom.Element;

import java.util.function.Function;

import static net.kcww.app.robogen.parser.model.XsdAttributeEnum.*;
import static net.kcww.app.robogen.parser.helper.Elements.setAttributeIfPresent;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XsdElements {

  public static XsdElementModel create(Element element) {
    XsdElementModel model = new XsdElementModel();
    setAttributes(element, model);
    setRestriction(element, model);
    return model;
  }

  private static void setAttributes(Element element, XsdElementModel model) {
    setAttributeIfPresent(element, NAME.getName(), Function.identity(), model::setName);
    setAttributeIfPresent(element, TYPE.getName(), Elements::stripPrefix, model::setType);
    setAttributeIfPresent(element, NILLABLE.getName(), Boolean::valueOf, model::setNillable);
    setAttributeIfPresent(element, MIN_OCCURS.getName(), Integer::valueOf, model::setMinOccurs);
    setAttributeIfPresent(element, MAX_OCCURS.getName(), Integer::valueOf, model::setMaxOccurs);
  }

  private static void setRestriction(Element element, XsdElementModel model) {
    XsdRestrictions.get(element).ifPresent(model::setRestriction);
  }
}
