package net.kcww.app.robogen.translator.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.kcww.app.robogen.translator.model.Widget;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum WidgetEnum implements Widget {

    GWT_BUTTON("Button", Type.BUTTON),
    GWT_PUSH_BUTTON("PushButton", Type.BUTTON),
    GWT_TOGGLE_BUTTON("ToggleButton", Type.BUTTON),
    GWT_RESET_BUTTON("ResetButton", Type.RESET),
    GWT_SUBMIT_BUTTON("SubmitButton", Type.SUBMIT),

    GWT_CHECK_BOX("CheckBox", Type.CHECKBOX),
    GWT_SIMPLE_CHECK_BOX("SimpleCheckBox", Type.CHECKBOX),

    GWT_FILE_UPLOAD("FileUpload", Type.FILE),

    GWT_IMAGE("Image", Type.IMAGE),

    GWT_ANCHOR("Anchor", Type.LINK),

    GWT_LIST_BOX("ListBox", Type.SELECT),

    GWT_RADIO_BUTTON("RadioButton", Type.RADIO),
    GWT_SIMPLE_RADIO_BUTTON("SimpleRadioButton", Type.RADIO),

    GWT_DATE_BOX("DateBox", Type.DATE),

    GWT_PASSWORD_TEXT_BOX("PasswordTextBox", Type.PASSWORD),

    GWT_DOUBLE_BOX("DoubleBox", Type.NUMBER),
    GWT_INTEGER_BOX("IntegerBox", Type.NUMBER),
    GWT_LONG_BOX("LongBox", Type.NUMBER),

    GWT_SUGGEST_BOX("SuggestBox", Type.TEXT),
    GWT_TEXT_BOX("TextBox", Type.TEXT),

    GWT_RICH_TEXT_AREA("RichTextArea", Type.TEXTAREA),
    GWT_TEXT_AREA("TextArea", Type.TEXTAREA);

//    VAADIN_BUTTON("vaadin-button", Type.BUTTON),
//    VAADIN_CHECKBOX("vaadin-checkbox", Type.CHECKBOX),
//    VAADIN_UPLOAD("vaadin-upload", Type.FILE),
//    VAADIN_COMBO_BOX("vaadin-combo-box", Type.SELECT),
//    VAADIN_SELECT("vaadin-select", Type.SELECT),
//    VAADIN_PASSWORD_FIELD("vaadin-password-field", Type.PASSWORD),
//    VAADIN_RADIO_GROUP("vaadin-radio-group", Type.RADIO),
//    VAADIN_DATE_PICKER("vaadin-date-picker", Type.DATE),
//    VAADIN_NUMBER_FIELD("vaadin-number-field", Type.NUMBER),
//    VAADIN_TEXT_FIELD("vaadin-text-field", Type.TEXT),
//    VAADIN_TEXT_AREA("vaadin-text-area", Type.TEXTAREA);

    private final String tagName;
    private final Type type;
}
