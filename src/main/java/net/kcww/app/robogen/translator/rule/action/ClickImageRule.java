package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class ClickImageRule extends AbstractElementRule {

    public ClickImageRule() {
        super(SeleniumElementActionKeywordEnum.CLICK_IMAGE);
    }
}
