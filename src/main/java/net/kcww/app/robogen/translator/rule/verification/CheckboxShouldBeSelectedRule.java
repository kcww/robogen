package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class CheckboxShouldBeSelectedRule extends AbstractVerificationRule {

  CheckboxShouldBeSelectedRule() {
    super(SeleniumElementVerificationKeywordEnum.CHECK_BOX_SHOULD_BE_SELECTED);
  }
}