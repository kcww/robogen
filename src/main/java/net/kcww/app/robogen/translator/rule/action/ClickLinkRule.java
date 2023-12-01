package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementActionRule;
import org.springframework.stereotype.Service;

@Service
public final class ClickLinkRule extends AbstractElementActionRule {

    public ClickLinkRule() {
        super(SeleniumElementActionKeywordEnum.CLICK_LINK);
    }
}
