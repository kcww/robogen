package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class SelectFromListByLabelRule extends AbstractElementRule {

    public SelectFromListByLabelRule() {
        super(SeleniumElementActionKeywordEnum.SELECT_FROM_LIST_BY_LABEL);
    }
}
