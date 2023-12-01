package net.kcww.app.robogen.parser.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Elements {

    public static Stream<Element> toStream(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(Element.class::isInstance)
                .map(Element.class::cast);
    }

    public static String stripPrefix(String name) {
        return name.contains(":") ? name.substring(name.indexOf(':') + 1) : name;
    }

    public static <T> void setAttributeIfPresent(Element element, String attrName,
                                                 Function<String, T> mapper, Consumer<T> setter) {

        Optional.of(element.getAttribute(attrName))
                .filter(value -> !value.isBlank())
                .map(mapper)
                .ifPresent(setter);
    }
}
