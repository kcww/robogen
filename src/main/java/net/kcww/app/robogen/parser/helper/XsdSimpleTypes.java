package net.kcww.app.robogen.parser.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.w3c.dom.Element;

import java.util.Optional;

import static net.kcww.app.robogen.parser.helper.Elements.toStream;
import static net.kcww.app.robogen.parser.model.XsdElementEnum.SIMPLE_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XsdSimpleTypes {

    // To check if the element has a SimpleType element as a direct child
    public static boolean hasSimpleTypeAsDirectChild(Element element) {
        return toStream(element.getChildNodes())
                .anyMatch(child -> SIMPLE_TYPE.getName().equals(child.getLocalName()));
    }

    // To get the first SimpleType element
    public static Optional<Element> get(Element element) {
        return toStream(element.getChildNodes())
                .filter(child -> SIMPLE_TYPE.getName().equals(child.getLocalName()))
                .findFirst();
    }
}
