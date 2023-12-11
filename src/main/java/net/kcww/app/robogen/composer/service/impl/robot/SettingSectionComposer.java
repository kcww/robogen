package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.ComposingMaterialModel;
import net.kcww.app.robogen.composer.model.RobotEnum;

import java.util.Arrays;

import static net.kcww.app.robogen.composer.helper.Composers.*;

public class SettingSectionComposer extends AbstractComposer {

    @Override
    public String compose(ComposingMaterialModel model) {
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
        append(RobotEnum.SETTINGS_SECTION.getKeyword());
    }

    private void documentation(ComposingMaterialModel model) {
        var documentation = RobotEnum.DOCUMENTATION.getKeyword();
        newlineAppend(documentation);
        var name = model.getFeatureName();
        if (name != null) {
            indentedAppend(name);
        }
        var featureDescription = model.getFeatureDescription();
        if (featureDescription != null) { // Handle multi-line feature descriptions
            String indentedSpace = " ".repeat(documentation.length());
            Arrays.stream(featureDescription.split("\\r?\\n"))
                    .forEach(line -> newlineAppend(indentedSpace).indentedAppend(line));
        }
    }

    private void library(String library) {
        newlineAppend(RobotEnum.LIBRARY.getKeyword()).indentedAppend(library);
    }

    private void testSetup() {
        newlineAppend(RobotEnum.TEST_SETUP.getKeyword()).indentedAppend(START_WEB_TEST);
    }

    private void testTeardown() {
        newlineAppend(RobotEnum.TEST_TEARDOWN.getKeyword()).indentedAppend(END_WEB_TEST);
    }

    private void testTemplate(ComposingMaterialModel model) {
        newlineAppend(RobotEnum.TEST_TEMPLATE.getKeyword()).indentedAppend(model.getScenarioName());
    }
}
