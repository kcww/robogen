package net.kcww.app.robogen.translator.helper;

import net.kcww.app.robogen.translator.model.selenium.SeleniumElementActionKeywordEnum;
import net.kcww.app.robogen.translator.model.selenium.SeleniumElementVerificationKeywordEnum;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumWaitingKeywordEnum;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Widgets {

    // Selenium element names
    public enum Element {
        BUTTON, CHECKBOX, FILE_UPLOAD, IMAGE, LINK, LIST, PASSWORD, RADIO, TEXT;
    }

    public static Set<SeleniumKeyword> getAptSeleniumKeywords(Element name) {
        var keywords = getCommonElementValidationKeywords();
        switch (name) {
            case BUTTON:
                keywords.add(SeleniumElementActionKeywordEnum.CLICK_BUTTON);
                break;
            case CHECKBOX:
                keywords.add(SeleniumElementActionKeywordEnum.SELECT_CHECKBOX);
                keywords.add(SeleniumElementVerificationKeywordEnum.CHECK_BOX_SHOULD_BE_SELECTED);
                keywords.add(SeleniumElementVerificationKeywordEnum.CHECK_BOX_SHOULD_NOT_BE_SELECTED);
                break;
            case FILE_UPLOAD:
                keywords.add(SeleniumElementActionKeywordEnum.CHOOSE_FILE);
                return Collections.unmodifiableSet(keywords);
            case IMAGE:
                keywords.add(SeleniumElementActionKeywordEnum.CLICK_IMAGE);
                break;
            case LINK:
                keywords.add(SeleniumElementActionKeywordEnum.CLICK_LINK);
                break;
            case LIST:
                keywords.add(SeleniumElementActionKeywordEnum.SELECT_FROM_LIST_BY_LABEL);
                keywords.add(SeleniumElementVerificationKeywordEnum.LIST_SELECTION_SHOULD_BE);
                keywords.add(SeleniumElementVerificationKeywordEnum.LIST_SHOULD_HAVE_NO_SELECTIONS);
                break;
            case PASSWORD:
                keywords.add(SeleniumElementActionKeywordEnum.INPUT_PASSWORD);
                keywords.add(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            case RADIO:
                keywords.add(SeleniumElementActionKeywordEnum.SELECT_RADIO_BUTTON);
                keywords.add(SeleniumElementVerificationKeywordEnum.RADIO_BUTTON_SHOULD_BE_SET_TO);
                keywords.add(SeleniumElementVerificationKeywordEnum.RADIO_BUTTON_SHOULD_NOT_BE_SELECTED);
                keywords.add(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            case TEXT:
                keywords.add(SeleniumElementActionKeywordEnum.INPUT_TEXT);
                keywords.add(SeleniumElementVerificationKeywordEnum.TEXTFIELD_SHOULD_CONTAIN);
                keywords.add(SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            default:
                return Collections.emptySet();
        }
        return Collections.unmodifiableSet(keywords);
    }

    public static Set<SeleniumKeyword> getCommonElementValidationKeywords() {
        return new HashSet<>(Set.of(
                SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_ENABLED,
                SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_DISABLED,
                SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_BE_VISIBLE,
                SeleniumElementVerificationKeywordEnum.ELEMENT_SHOULD_NOT_BE_VISIBLE,
                SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_IS_ENABLED,
                SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_IS_VISIBLE,
                SeleniumWaitingKeywordEnum.WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE));
    }
}
