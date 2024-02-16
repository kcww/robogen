package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.RobotScript;
import net.kcww.app.robogen.composer.helper.RobotScriptEnum;

import static net.kcww.app.robogen.composer.helper.RobotScripts.*;

public class VariableSectionWriter extends AbstractScriptSectionWriter {

    @Override
    public String write(RobotScript model) {
        header();                 // *** Variables ***
        browser(DEFAULT_BROWSER); // ${BROWSER}    chrome
        url(model.getUrl());         // ${URL}        http://localhost:8080/
        delay(DEFAULT_DELAY);     // ${DELAY}      0.5
        timeout(DEFAULT_TIMEOUT); // ${TIMEOUT}    10
        return draft();
    }

    private void header() {
        newlineAppend(RobotScriptEnum.VARIABLES_SECTION.getKeyword());
    }

    private void browser(String browser) {
        newlineAppend($_BROWSER).indentedAppend(browser);
    }

    private void url(String url) {
        newlineAppend($_URL).indentedAppend(url);
    }

    private void delay(String delay) {
        newlineAppend($_DELAY).indentedAppend(delay);
    }

    private void timeout(String timeout) {
        newlineAppend($_TIMEOUT).indentedAppend(timeout);
    }
}
