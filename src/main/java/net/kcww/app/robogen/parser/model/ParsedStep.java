package net.kcww.app.robogen.parser.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(fluent = true)
public class ParsedStep implements Comparable<ParsedStep> {

    private final long order;   // line number in the feature file
    @Builder.Default
    private Type type = Type.UNKNOWN;
    private final String text;

    @Override
    public int compareTo(ParsedStep other) {
        return Long.compare(this.order, other.order);
    }

    public enum Type {
        GIVEN, WHEN, THEN, AND, UNKNOWN
    }
}
