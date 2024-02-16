package net.kcww.app.robogen.translator.rule.action;

import net.kcww.app.robogen.translator.model.SeleniumKeyword;
import net.kcww.app.robogen.translator.helper.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.rule.AbstractElementRule;
import org.springframework.stereotype.Service;

@Service
public final class InputTextRule extends AbstractElementRule {

    public static final SeleniumKeyword SELENIUM_KEYWORD = SeleniumKeywordEnum.INPUT_TEXT;

    public InputTextRule() {
        super(SELENIUM_KEYWORD);
    }
}

// Input Text: [GWT_DATE_BOX, GWT_DOUBLE_BOX, GWT_INTEGER_BOX, GWT_LONG_BOX, GWT_RICH_TEXT_AREA,
//              GWT_SUGGEST_BOX, GWT_TEXT_BOX, GWT_TEXT_AREA, VAADIN_DATE_PICKER, VAADIN_INTEGER_FIELD,
//              VAADIN_TEXT_FIELD, VAADIN_TEXT_AREA]
// INPUT_TEXT("Input Text", ACTION, hasLocAndArg()),    // locator, text, clear = true (Ignored)

// isApplicable: xmlElement().tagName()
// translate: keyword, {locator, text}

// And I input "John" into firstName
// And I input "Doe" into lastName
// And I input <city>
// And I input <title>

// <g:TextBox debugId="firstName" />
// <a:TextField id="lastName" />
// <g:TextBox debugId="city" />
// <a:TextField id="title" />