package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.ComposingMaterialModel;
import net.kcww.app.robogen.composer.model.RobotEnum;

import static net.kcww.app.robogen.composer.helper.Composers.*;

public class VariableSectionComposer extends AbstractComposer {

  @Override
  public String compose(ComposingMaterialModel model) {
    header();                 // *** Variables ***
    browser(DEFAULT_BROWSER); // ${BROWSER}    chrome
    url(DEFAULT_URL);         // ${URL}        http://localhost:8080/
    delay(DEFAULT_DELAY);     // ${DELAY}      0.5
    timeout(DEFAULT_TIMEOUT); // ${TIMEOUT}    10
    return draft();
  }

  private void header() {
    newlineAppend(RobotEnum.VARIABLES_SECTION.getKeyword());
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
