package net.kcww.app.robogen.translator.rule.verification;

import net.kcww.app.robogen.mapper.model.RelationModel;
import net.kcww.app.robogen.translator.model.KeywordModel;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.widget.Widget;
import net.kcww.app.robogen.translator.rule.KeywordRule;

import java.util.List;

import static net.kcww.app.robogen.translator.helper.Rules.collectRelevantWidgets;

public abstract class AbstractVerificationRule implements KeywordRule<RelationModel, KeywordModel> {
    protected final SeleniumKeyword seleniumKeyword;
    protected final List<Widget> aptWidgets;

    protected AbstractVerificationRule(SeleniumKeyword seleniumKeyword) {
        this.seleniumKeyword = seleniumKeyword;
        this.aptWidgets = collectRelevantWidgets(seleniumKeyword);
    }

    @Override
    public boolean isApplicable(RelationModel relation) {
        return false;
    }

    @Override
    public KeywordModel translate(RelationModel relationModel) {
        return null;
    }
}
