package net.kcww.app.robogen.translator.model.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;

import java.util.Set;

import static net.kcww.app.robogen.translator.helper.Widgets.*;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum VaadinWidgetEnum implements Widget {

    BUTTON("Button", getAptSeleniumKeywords(Element.BUTTON)),

    CHECKBOX("Checkbox", getAptSeleniumKeywords(Element.CHECKBOX)),

    UPLOAD("Upload", getAptSeleniumKeywords(Element.FILE_UPLOAD)),

    IMAGE("Image", getAptSeleniumKeywords(Element.IMAGE)),

    COMBOBOX("ComboBox", getAptSeleniumKeywords(Element.LIST)),
    SELECT("Select", getAptSeleniumKeywords(Element.LIST)),

    PASSWORD_FIELD("PasswordField", getAptSeleniumKeywords(Element.PASSWORD)),

    RADIO_GROUP("RadioGroup", getAptSeleniumKeywords(Element.RADIO)),

    DATE_PICKER("DatePicker", getAptSeleniumKeywords(Element.TEXT)),
    INTEGER_FIELD("IntegerField", getAptSeleniumKeywords(Element.TEXT)),
    TEXT_FIELD("TextField", getAptSeleniumKeywords(Element.TEXT)),
    TEXT_AREA("TextArea", getAptSeleniumKeywords(Element.TEXT));

    private final String tag;
    private final Set<SeleniumKeyword> aptKeywords;
}
