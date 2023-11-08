package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.MaterialModel;

public class TestcaseSectionComposer extends AbstractComposer {

  private static final String HEADER = "*** Test Cases ***";

  @Override
  public String compose(MaterialModel model) {
    header();            // *** Test Cases ***
    userKeyword(model);  // <scenario-name>
    return draft();
  }

  private void header() {
    newlineAppend(HEADER);
  }

  private void userKeyword(MaterialModel model) {
    newlineAppend(model.getScenarioName());
  }
}
