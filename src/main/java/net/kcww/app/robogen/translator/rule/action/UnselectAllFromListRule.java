package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class UnselectAllFromListRule extends AbstractElementRule {

    public UnselectAllFromListRule() {
        super(SeleniumElementActionKeywordEnum.UNSELECT_ALL_FROM_LIST);
    }
}
