package net.kcww.app.robogen.composer.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RobotScriptEnum {

    SETTINGS_SECTION("*** Settings ***"),
    VARIABLES_SECTION("*** Variables ***"),
    TEST_CASE_SECTION("*** Test Cases ***"),
    KEYWORDS_SECTION("*** Keywords ***"),
    DOCUMENTATION("Documentation"),
    LIBRARY("Library"),
    TEST_SETUP("Test Setup"),
    TEST_TEARDOWN("Test Teardown"),
    TEST_TEMPLATE("Test Template"),
    ARGUMENTS("[Arguments]");

    private String keyword;
}
