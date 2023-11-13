package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class UnselectFromListByLabelRule extends AbstractElementRule {

    public UnselectFromListByLabelRule() {
        super(SeleniumElementActionKeywordEnum.UNSELECT_FROM_LIST_BY_LABEL);
    }
}
