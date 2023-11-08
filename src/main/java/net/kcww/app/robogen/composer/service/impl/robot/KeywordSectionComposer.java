package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.MaterialModel;

import static net.kcww.app.robogen.composer.helper.Composers.*;
import static net.kcww.app.robogen.composer.service.impl.robot.VariableSectionComposer.*;

public class KeywordSectionComposer extends AbstractComposer {

  private static final String HEADER = "*** Keywords ***";
  private static final String OPEN_BROWSER = "Open Browser";
  private static final String BLANK_PAGE = "about:blank";
  private static final String MAXIMIZE_BROWSER_WINDOW = "Maximize Browser Window";
  private static final String SET_SELENIUM_SPEED = "Set Selenium Speed";
  private static final String SET_SELENIUM_TIMEOUT = "Set Selenium Timeout";
  private static final String SET_SELENIUM_IMPLICIT_WAIT = "Set Selenium Implicit Wait";
  private static final String GO_TO = "Go To";
  private static final String CLOSE_BROWSER = "Close Browser";
  private static final String ARGUMENTS = "[Arguments]";

  @Override
  public String compose(MaterialModel model) {
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
    newlineAppend(HEADER);
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

  private void userKeyword(MaterialModel model) {
    newlineAppend(model.getScenarioName()); // <User Keyword>
    var arguments = extractArguments(model.getKeywordModels());
    if (!arguments.isEmpty()) {             //    [Arguments]    ${ARG1}    ${ARG2}    ${ARG3}    ...
      newlineIndentedAppend(ARGUMENTS).indentedAppend(String.join(INDENT, arguments));
    }                                       //    <Test Step>
    formatTestSteps(model.getKeywordModels()).forEach(this::newlineIndentedAppend);
  }

  private void startWebTest() {
    newlineAppend(START_WEB_TEST);
  }

  private void openBrowser() {
    newlineIndentedAppend(OPEN_BROWSER).indentedAppend(BLANK_PAGE).indentedAppend($_BROWSER);
  }

  private void maximizeBrowserWindow() {
    newlineIndentedAppend(MAXIMIZE_BROWSER_WINDOW);
  }

  private void setSeleniumSpeed() {
    newlineIndentedAppend(SET_SELENIUM_SPEED).indentedAppend($_DELAY);
  }

  private void setSeleniumTimeout() {
    newlineIndentedAppend(SET_SELENIUM_TIMEOUT).indentedAppend($_TIMEOUT);
  }

  private void setSeleniumImplicitWait() {
    newlineIndentedAppend(SET_SELENIUM_IMPLICIT_WAIT).indentedAppend($_TIMEOUT);
  }

  private void goTo() {
    newlineIndentedAppend(GO_TO).indentedAppend($_URL);
  }

  private void endWebTest() {
    newlineAppend(END_WEB_TEST);
  }

  private void closeBrowser() {
    newlineIndentedAppend(CLOSE_BROWSER);
  }
}
