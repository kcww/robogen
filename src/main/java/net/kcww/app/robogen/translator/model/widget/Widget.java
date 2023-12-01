package net.kcww.app.robogen.translator.model.widget;

import java.util.Set;

public interface Widget {

    // Widget's tag name
    String tagName();

    // Relevant Selenium keywords for this Widget
    <T> Set<? extends T> aptKeywords();
}
