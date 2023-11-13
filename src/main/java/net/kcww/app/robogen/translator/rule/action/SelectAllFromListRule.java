package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import org.springframework.stereotype.Service;

@Service
public final class SelectAllFromListRule extends AbstractElementRule {

    public SelectAllFromListRule() {
        super(SeleniumElementActionKeywordEnum.SELECT_ALL_FROM_LIST);
    }
}
