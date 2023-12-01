package net.kcww.app.robogen.translator.model.selenium;

public interface SeleniumKeyword {

    String keyword();
    boolean hasLocator();
    boolean hasArgument();

    boolean HAS_LOC = true;
    boolean NO_LOC = false;
    boolean HAS_ARG = true;
    boolean NO_ARG = false;

}