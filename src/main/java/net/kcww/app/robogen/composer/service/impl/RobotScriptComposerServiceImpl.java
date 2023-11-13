package net.kcww.app.robogen.composer.service.impl;

import lombok.AllArgsConstructor;
import net.kcww.app.robogen.composer.model.ComposingMaterialModel;
import net.kcww.app.robogen.composer.service.ComposerService;
import net.kcww.app.robogen.composer.service.impl.robot.KeywordSectionComposer;
import net.kcww.app.robogen.composer.service.impl.robot.SettingSectionComposer;
import net.kcww.app.robogen.composer.service.impl.robot.TestcaseSectionComposer;
import net.kcww.app.robogen.composer.service.impl.robot.VariableSectionComposer;
import org.springframework.stereotype.Service;

import static net.kcww.app.robogen.composer.helper.Composers.NEW_LINE;

@Service
@AllArgsConstructor
public class RobotScriptComposerServiceImpl implements ComposerService<ComposingMaterialModel, String> {

  @Override
  public String compose(ComposingMaterialModel input) {
    return String.join(NEW_LINE,
                       new SettingSectionComposer().compose(input),
                       new VariableSectionComposer().compose(input),
                       new TestcaseSectionComposer().compose(input),
                       new KeywordSectionComposer().compose(input));
  }
}
