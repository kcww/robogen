package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class ChooseFileRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.CHOOSE_FILE;

    public ChooseFileRule() {
        super(SELENIUM_KEYWORD);
    }
}

// Choose File: [GWT_FILE_UPLOAD, VAADIN_UPLOAD]
// CHOOSE_FILE("Choose File", ACTION, hasLocAndArg()),      // locator, file_path

// isApplicable: xmlElement().tagName()
// translate: keyword, {locator, file_path}

// When I type "my-citizen-card.png" in id-card
// And I choose <bookBank>

// <g:FileUpload label="ID Card" debugId="id-card" />
// <g:FileUpload label="Book Bank" id="bookBank" />