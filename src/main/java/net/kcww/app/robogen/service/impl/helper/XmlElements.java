package net.kcww.app.robogen.service.impl.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.model.XmlElementModel;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static net.kcww.app.robogen.model.XmlAttribute.*;
import static net.kcww.app.robogen.model.XsdAttribute.VALUE;
import static net.kcww.app.robogen.service.impl.helper.Elements.setAttributeIfPresent;
import static net.kcww.app.robogen.service.impl.helper.Elements.stripPrefix;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XmlElements {

  private static final List<String> identities = Arrays.asList(ID.getName(), DEBUG_ID.getName(), NAME.getName());

  public static XmlElementModel create(Element element) {
    var model = new XmlElementModel();
    model.setTag(stripPrefix(element.getNodeName()));
    setAttributes(element, model);
    return model;
  }

  /**
   * Checks if the provided element has a valid attribute.
   *
   * @param element XML element
   * @return true if valid, false otherwise
   */
  public static boolean hasValidAttribute(Element element) {
    return identities.stream().anyMatch(element::hasAttribute);
  }

  private static void setAttributes(Element element, XmlElementModel model) {
    String idValue = element.hasAttribute(DEBUG_ID.getName()) ?
      element.getAttribute(DEBUG_ID.getName()) :
      element.getAttribute(ID.getName());
    model.setId(idValue);

    setAttributeIfPresent(element, NAME.getName(), Function.identity(), model::setName);
    setAttributeIfPresent(element, LABEL.getName(), Function.identity(), model::setLabel);
    setAttributeIfPresent(element, VALUE.getName(), Function.identity(), model::setValue);
    setAttributeIfPresent(element, MIN.getName(), Function.identity(), model::setMin);
    setAttributeIfPresent(element, MAX.getName(), Function.identity(), model::setMax);
    setAttributeIfPresent(element, REQUIRED.getName(), Boolean::valueOf, model::setRequired);
  }
}