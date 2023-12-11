package net.kcww.app.robogen.translator.model.widget;

public interface Widget {

    enum Type {
        BUTTON, CHECKBOX, FILE_UPLOAD, IMAGE, LINK, LIST, PASSWORD, RADIO, TEXT, TEXT_AREA
    }

    String tagName();
    Widget.Type type();
}
