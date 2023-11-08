package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.MaterialModel;

import java.util.Arrays;

import static net.kcww.app.robogen.composer.helper.Composers.END_WEB_TEST;
import static net.kcww.app.robogen.composer.helper.Composers.START_WEB_TEST;

public class SettingSectionComposer extends AbstractComposer {

  private static final String HEADER = "*** Settings ***";
  private static final String DOCUMENTATION = "Documentation";
  private static final String LIBRARY = "Library";
  private static final String TEST_SETUP = "Test Setup";
  private static final String TEST_TEARDOWN = "Test Teardown";
  private static final String TEST_TEMPLATE = "Test Template";

  private static final String SELENIUM_LIBRARY = "SeleniumLibrary";
  private static final String DATA_DRIVER = "DataDriver";

  @Override
  public String compose(MaterialModel model) {
    header();                   // *** Settings ***
    documentation(model);       // Documentation  <feature-name>
    //                                            <feature-description>
    library(SELENIUM_LIBRARY);  // Library        SeleniumLibrary
    library(DATA_DRIVER);       // Library        DataDriver
    testSetup();                // Test Setup     Start Web Test
    testTeardown();             // Test Teardown  END Web Test
    testTemplate(model);        // Test Template  <scenario-name>
    return draft();
  }

  private void header() {
    append(HEADER);
  }

  private void documentation(MaterialModel model) {
    append(DOCUMENTATION);
    var name = model.getFeatureName();
    if (name != null) {
      indentedAppend(name);
    }
    var featureDescription = model.getFeatureDescription();
    if (featureDescription != null) { // Handle multi-line feature descriptions
      String indentedSpace = " ".repeat(DOCUMENTATION.length());
      Arrays.stream(featureDescription.split("\\r?\\n"))
        .forEach(line -> newlineAppend(indentedSpace).indentedAppend(line));
    }
  }

  private void library(String library) {
    newlineAppend(LIBRARY).indentedAppend(library);
  }

  private void testSetup() {
    newlineAppend(TEST_SETUP).indentedAppend(START_WEB_TEST);
  }

  private void testTeardown() {
    newlineAppend(TEST_TEARDOWN).indentedAppend(END_WEB_TEST);
  }

  private void testTemplate(MaterialModel model) {
    newlineAppend(TEST_TEMPLATE).indentedAppend(model.getScenarioName());
  }
}
