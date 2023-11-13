package net.kcww.app.robogen.composer.service.impl.robot;

import net.kcww.app.robogen.composer.model.ComposingMaterialModel;
import net.kcww.app.robogen.composer.service.ComposerService;

import static net.kcww.app.robogen.composer.helper.Composers.INDENT;
import static net.kcww.app.robogen.composer.helper.Composers.NEW_LINE;

public abstract class AbstractComposer implements ComposerService<ComposingMaterialModel, String> {

  private final StringBuilder builder = new StringBuilder();

  protected String draft() {
    return builder.toString();
  }

  protected AbstractComposer append(String text) {
    builder.append(text);
    return this;
  }

  protected AbstractComposer newline() {
    return append(NEW_LINE);
  }

  protected AbstractComposer newlineAppend(String text) {
    newline().append(text);
    return this;
  }

  public AbstractComposer indentedAppend(String text) {
    return append(INDENT).append(text);
  }

  protected AbstractComposer newlineIndentedAppend(String text) {
    return newline().append(INDENT).append(text);
  }
}
