package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.helper.RobotScriptEnum;
import net.kcww.app.robogen.composer.model.RobotScript;

import java.util.Arrays;

import static net.kcww.app.robogen.composer.helper.RobotScripts.*;

public class SettingSectionWriter extends AbstractScriptSectionWriter {

    @Override
    public String write(RobotScript model) {
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
        append(RobotScriptEnum.SETTINGS_SECTION.getKeyword());
    }

    private void documentation(RobotScript model) {
        var documentation = RobotScriptEnum.DOCUMENTATION.getKeyword();
        newlineAppend(documentation);

        indentedAppend(model.getFeatureName());

        var featureDescription = model.getFeatureDescription();
        var indentedSpace = " ".repeat(documentation.length());
        Arrays.stream(featureDescription.split("\\r?\\n"))
                .forEach(line -> newlineAppend(indentedSpace).indentedAppend(line));
    }

    private void library(String library) {
        newlineAppend(RobotScriptEnum.LIBRARY.getKeyword()).indentedAppend(library);
    }

    private void testSetup() {
        newlineAppend(RobotScriptEnum.TEST_SETUP.getKeyword()).indentedAppend(START_WEB_TEST);
    }

    private void testTeardown() {
        newlineAppend(RobotScriptEnum.TEST_TEARDOWN.getKeyword()).indentedAppend(END_WEB_TEST);
    }

    private void testTemplate(RobotScript model) {
        newlineAppend(RobotScriptEnum.TEST_TEMPLATE.getKeyword()).indentedAppend(model.getScenarioName());
    }
}
