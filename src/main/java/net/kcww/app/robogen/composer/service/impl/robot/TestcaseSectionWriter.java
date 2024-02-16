package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.helper.RobotScriptEnum;
import net.kcww.app.robogen.composer.model.RobotScript;

public class TestcaseSectionWriter extends AbstractScriptSectionWriter {

    @Override
    public String write(RobotScript model) {
        header();            // *** Test Cases ***
        userKeyword(model);  // <scenario-name>
        return draft();
    }

    private void header() {
        newlineAppend(RobotScriptEnum.TEST_CASE_SECTION.getKeyword());
    }

    private void userKeyword(RobotScript model) {
        newlineAppend(model.getScenarioName());
    }
}
