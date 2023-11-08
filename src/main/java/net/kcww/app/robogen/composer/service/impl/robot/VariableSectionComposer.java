package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.MaterialModel;

import static net.kcww.app.robogen.composer.helper.Composers.*;

public class VariableSectionComposer extends AbstractComposer {

  private static final String HEADER = "*** Variables ***";
  public static final String $_BROWSER = "${BROWSER}";
  public static final String $_URL = "${URL}";
  public static final String $_DELAY = "${DELAY}";
  public static final String $_TIMEOUT = "${TIMEOUT}";
  public static final String $_RETRY = "${RETRY}";

  @Override
  public String compose(MaterialModel model) {
    header();                 // *** Variables ***
    browser(DEFAULT_BROWSER); // ${BROWSER}    chrome
    url(DEFAULT_URL);         // ${URL}        http://localhost:8080/
    delay(DEFAULT_DELAY);     // ${DELAY}      0.5
    timeout(DEFAULT_TIMEOUT); // ${TIMEOUT}    10
    retry(DEFAULT_RETRY);     // ${RETRY}      3
    return draft();
  }

  private void header() {
    newlineAppend(HEADER);
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

  private void retry(String retry) {
    newlineAppend($_RETRY).indentedAppend(retry);
  }
}
