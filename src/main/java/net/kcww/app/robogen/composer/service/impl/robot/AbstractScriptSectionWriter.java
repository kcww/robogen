package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.RobotScript;

import static net.kcww.app.robogen.composer.helper.RobotScripts.INDENT;
import static net.kcww.app.robogen.composer.helper.RobotScripts.NEW_LINE;

public abstract class AbstractScriptSectionWriter {

    private final StringBuilder builder = new StringBuilder();

    public abstract String write(RobotScript model);

    protected String draft() {
        return builder.toString();
    }

    protected AbstractScriptSectionWriter append(String text) {
        builder.append(text);
        return this;
    }

    protected AbstractScriptSectionWriter newline() {
        return append(NEW_LINE);
    }

    protected AbstractScriptSectionWriter newlineAppend(String text) {
        newline().append(text);
        return this;
    }

    public AbstractScriptSectionWriter indentedAppend(String text) {
        return append(INDENT).append(text);
    }

    protected AbstractScriptSectionWriter newlineIndentedAppend(String text) {
        return newline().append(INDENT).append(text);
    }
}
