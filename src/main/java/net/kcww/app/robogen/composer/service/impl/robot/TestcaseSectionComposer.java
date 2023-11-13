package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.ComposingMaterialModel;
import net.kcww.app.robogen.composer.model.RobotEnum;

public class TestcaseSectionComposer extends AbstractComposer {

  @Override
  public String compose(ComposingMaterialModel model) {
    header();            // *** Test Cases ***
    userKeyword(model);  // <scenario-name>
    return draft();
  }

  private void header() {
    newlineAppend(RobotEnum.TEST_CASE_SECTION.getKeyword());
  }

  private void userKeyword(ComposingMaterialModel model) {
    newlineAppend(model.getScenarioName());
  }
}
