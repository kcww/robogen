package net.kcww.app.robogen.translator.model.widget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum WidgetEnum implements Widget {

    GWT_BUTTON("Button", Type.BUTTON),
    GWT_PUSH_BUTTON("PushButton", Type.BUTTON),
    GWT_RESET_BUTTON("ResetButton", Type.BUTTON),
    GWT_SUBMIT_BUTTON("SubmitButton", Type.BUTTON),
    GWT_TOGGLE_BUTTON("ToggleButton", Type.BUTTON),
    GWT_CHECK_BOX("CheckBox", Type.CHECKBOX),
    GWT_SIMPLE_CHECK_BOX("SimpleCheckBox", Type.CHECKBOX),
    GWT_FILE_UPLOAD("FileUpload", Type.FILE_UPLOAD),
    GWT_IMAGE("Image", Type.IMAGE),
    GWT_ANCHOR("Anchor", Type.LINK),
    GWT_LIST_BOX("ListBox", Type.LIST),
    GWT_PASSWORD_TEXT_BOX("PasswordTextBox", Type.PASSWORD),
    GWT_RADIO_BUTTON("RadioButton", Type.RADIO),
    GWT_SIMPLE_RADIO_BUTTON("SimpleRadioButton", Type.RADIO),
    GWT_DATE_BOX("DateBox", Type.TEXT),
    GWT_DOUBLE_BOX("DoubleBox", Type.TEXT),
    GWT_INTEGER_BOX("IntegerBox", Type.TEXT),
    GWT_LONG_BOX("LongBox", Type.TEXT),
    GWT_RICH_TEXT_AREA("RichTextArea", Type.TEXT_AREA),
    GWT_SUGGEST_BOX("SuggestBox", Type.TEXT),
    GWT_TEXT_BOX("TextBox", Type.TEXT),
    GWT_TEXT_AREA("TextArea", Type.TEXT_AREA),

    VAADIN_BUTTON("Button", Type.BUTTON),
    VAADIN_CHECKBOX("Checkbox", Type.CHECKBOX),
    VAADIN_UPLOAD("Upload", Type.FILE_UPLOAD),
    VAADIN_IMAGE("Image", Type.IMAGE),
    VAADIN_COMBO_BOX("ComboBox", Type.LIST),
    VAADIN_SELECT("Select", Type.LIST),
    VAADIN_PASSWORD_FIELD("PasswordField", Type.PASSWORD),
    VAADIN_RADIO_GROUP("RadioGroup", Type.RADIO),
    VAADIN_DATE_PICKER("DatePicker", Type.TEXT),
    VAADIN_INTEGER_FIELD("IntegerField", Type.TEXT),
    VAADIN_TEXT_FIELD("TextField", Type.TEXT),
    VAADIN_TEXT_AREA("TextArea", Type.TEXT_AREA);

    private final String tagName;
    private final Type type;
}
