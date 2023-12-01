package net.kcww.app.robogen.translator.model.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;

import java.util.Set;

import static net.kcww.app.robogen.translator.helper.Widgets.Element;
import static net.kcww.app.robogen.translator.helper.Widgets.getAptSeleniumKeywords;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum GwtWidgetEnum implements Widget {

    BUTTON("Button", getAptSeleniumKeywords(Element.BUTTON)),
    PUSH_BUTTON("PushButton", getAptSeleniumKeywords(Element.BUTTON)),
    RESET_BUTTON("ResetButton", getAptSeleniumKeywords(Element.BUTTON)),
    SUBMIT_BUTTON("SubmitButton", getAptSeleniumKeywords(Element.BUTTON)),
    TOGGLE_BUTTON("ToggleButton", getAptSeleniumKeywords(Element.BUTTON)),

    CHECKBOX("CheckBox", getAptSeleniumKeywords(Element.CHECKBOX)),
    SIMPLE_CHECKBOX("SimpleCheckBox", getAptSeleniumKeywords(Element.CHECKBOX)),

    FILE_UPLOAD("FileUpload", getAptSeleniumKeywords(Element.FILE_UPLOAD)),

    IMAGE("Image", getAptSeleniumKeywords(Element.IMAGE)),

    ANCHOR("Anchor", getAptSeleniumKeywords(Element.LINK)),

    LISTBOX("ListBox", getAptSeleniumKeywords(Element.LIST)),

    PASSWORD_TEXTBOX("PasswordTextBox", getAptSeleniumKeywords(Element.PASSWORD)),

    RADIO_BUTTON("RadioButton", getAptSeleniumKeywords(Element.RADIO)),
    SIMPLE_RADIO_BUTTON("SimpleRadioButton", getAptSeleniumKeywords(Element.RADIO)),

    DATEBOX("DateBox", getAptSeleniumKeywords(Element.TEXT)),
    DOUBLEBOX("DoubleBox", getAptSeleniumKeywords(Element.TEXT)),
    INTEGERBOX("IntegerBox", getAptSeleniumKeywords(Element.TEXT)),
    LONGBOX("LongBox", getAptSeleniumKeywords(Element.TEXT)),
    RICHTEXTAREA("RichTextArea", getAptSeleniumKeywords(Element.TEXT)),
    SUGGESTBOX("SuggestBox", getAptSeleniumKeywords(Element.TEXT)),
    TEXTBOX("TextBox", getAptSeleniumKeywords(Element.TEXT)),
    TEXTAREA("TextArea", getAptSeleniumKeywords(Element.TEXTAREA));

    private final String tagName;
    private final Set<SeleniumKeyword> aptKeywords;
}
