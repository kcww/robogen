package net.kcww.app.robogen.translator.model;

public interface Widget {

    String tagName();
    Type type();

    enum Type {
        // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input
        BUTTON, CHECKBOX, DATE, FILE, IMAGE, NUMBER, PASSWORD, RADIO, RESET, SUBMIT, TEXT,
        // https://developer.mozilla.org/en-US/docs/Web/HTML/Element
        LINK, SELECT, TEXTAREA
    }
}
