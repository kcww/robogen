package net.kcww.app.robogen.parser.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = true)
public class XsdElementModel {

    // XSD Attributes
    // e.g., <xs:element name="firstName" type="xs:string" nillable="true">
    //       <xs:element name="lastName" type="xs:string" minOccurs="1" maxOccurs="1">
    private String name;
    private String type;
    private boolean nillable = false;
    private int minOccurs = 1;
    private int maxOccurs = 1;

    // XSD Restriction element
    private Restriction restriction;

    @Data
    public static class Restriction {
        private String base;
        private String pattern;
        private int minLength = -1;
        private int maxLength = -1;
        private int minInclusive = -1;
        private int maxInclusive = -1;
        private List<String> enumerations;
    }
}
