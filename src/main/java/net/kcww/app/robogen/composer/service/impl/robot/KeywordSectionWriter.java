package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.RobotScript;
import net.kcww.app.robogen.composer.helper.RobotScriptEnum;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;

import static net.kcww.app.robogen.composer.helper.RobotScripts.*;

public class KeywordSectionWriter extends AbstractScriptSectionWriter {

    @Override
    public String write(RobotScript model) {
        header();                 // *** Keywords ***
        startWebTestKeyword();    // Start Web Test
        newline();
        endWebTestKeyword();      // End Web Test
        newline();
        userKeyword(model);       // <User Keyword>
        newline();
        return draft();
    }

    private void header() {
        newlineAppend(RobotScriptEnum.KEYWORDS_SECTION.getKeyword());
    }

    private void startWebTestKeyword() {
        startWebTest();             // Start Web Test
        openBrowser();              //    Open Browser   ${BLANK_PAGE}  ${BROWSER}
        maximizeBrowserWindow();    //    Maximize Browser Window
        setSeleniumSpeed();         //    Set Selenium Speed            ${DELAY}
        setSeleniumTimeout();       //    Set Selenium Timeout          ${TIMEOUT}
        setSeleniumImplicitWait();  //    Set Selenium Implicit Wait    ${TIMEOUT}
        goTo();                     //    Go To  ${URL}
    }

    private void endWebTestKeyword() {
        endWebTest();               // End Web Test
        closeBrowser();             //    Close Browser
    }

    private void userKeyword(RobotScript model) {
        newlineAppend(model.getScenarioName()); // <User Keyword>

        var arguments = extractArguments(model.getKeywords());
        if (!arguments.isEmpty()) {             //    [Arguments]    ${ARG1}    ${ARG2}    ${ARG3}    ...
            newlineIndentedAppend(RobotScriptEnum.ARGUMENTS.getKeyword()).indentedAppend(String.join(INDENT, arguments));
        }                                       //    <Test Step>
        formatTestSteps(model.getKeywords()).forEach(this::newlineIndentedAppend);
    }

    private void startWebTest() {
        newlineAppend(START_WEB_TEST);
    }

    private void openBrowser() {
        newlineIndentedAppend(SeleniumKeywordEnum.OPEN_BROWSER.keyword()).indentedAppend(BLANK_PAGE).indentedAppend($_BROWSER);
    }

    private void maximizeBrowserWindow() {
        newlineIndentedAppend(SeleniumKeywordEnum.MAXIMIZE_BROWSER_WINDOW.keyword());
    }

    private void setSeleniumSpeed() {
        newlineIndentedAppend(SeleniumKeywordEnum.SET_SELENIUM_SPEED.keyword()).indentedAppend($_DELAY);
    }

    private void setSeleniumTimeout() {
        newlineIndentedAppend(SeleniumKeywordEnum.SET_SELENIUM_TIMEOUT.keyword()).indentedAppend($_TIMEOUT);
    }

    private void setSeleniumImplicitWait() {
        newlineIndentedAppend(SeleniumKeywordEnum.SET_SELENIUM_IMPLICIT_WAIT.keyword()).indentedAppend($_TIMEOUT);
    }

    private void goTo() {
        newlineIndentedAppend(SeleniumKeywordEnum.GO_TO.keyword()).indentedAppend($_URL);
    }

    private void endWebTest() {
        newlineAppend(END_WEB_TEST);
    }

    private void closeBrowser() {
        newlineIndentedAppend(SeleniumKeywordEnum.CLOSE_BROWSER.keyword());
    }
}
