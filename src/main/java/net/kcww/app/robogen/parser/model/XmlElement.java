package net.kcww.app.robogen.parser.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.Accessors;

// UI Model
@Data
@Builder
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class XmlElement {

    // XML Element name
    // e.g., <a:TextField label="First name" debugId="firstName" required="true" clearButtonVisible="true" autoFocus="true"/>
    //       <a:IntegerField label="Number of Adults" debugId="adults" value="1" min="1" max="100" stepButtonsVisible="true"/>

    private String tagName;     // TextField        IntegerField

    // XML Attributes
    @NonNull @NotEmpty
    private String id;          // firstName        adults
    private String name;        // null             null
    private String label;       // First name       Number of Adults
    private String value;       // null             1
    private String min;         // null             1
    private String max;         // null             100
    private boolean required;   // true             null
}
