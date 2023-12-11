package net.kcww.app.robogen.translator.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeyword;
import net.kcww.app.robogen.translator.model.selenium.SeleniumKeywordEnum;
import net.kcww.app.robogen.translator.model.widget.Widget;
import net.kcww.app.robogen.translator.model.widget.WidgetEnum;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Widgets {

    private static final Map<Widget.Type, EnumSet<SeleniumKeywordEnum>> WIDGET_TYPE_TO_KEYWORDS_MAP;
    private static final Map<SeleniumKeyword, EnumSet<WidgetEnum>> KEYWORD_TO_WIDGETS_MAP;

    static {
        WIDGET_TYPE_TO_KEYWORDS_MAP = createWidgetTypeToKeywordsMap();
        KEYWORD_TO_WIDGETS_MAP = createSeleniumKeywordToWidgetsMap();
    }

    public static EnumSet<WidgetEnum> getRelevantWidgets(SeleniumKeyword seleniumKeyword) {
        return KEYWORD_TO_WIDGETS_MAP.getOrDefault(seleniumKeyword, EnumSet.noneOf(WidgetEnum.class));
    }

    private static Map<SeleniumKeyword, EnumSet<WidgetEnum>> createSeleniumKeywordToWidgetsMap() {
        Map<SeleniumKeyword, EnumSet<WidgetEnum>> map = new HashMap<>();
        for (WidgetEnum widget : WidgetEnum.values()) {
            EnumSet<SeleniumKeywordEnum> keywords = WIDGET_TYPE_TO_KEYWORDS_MAP.get(widget.type());
            for (SeleniumKeywordEnum keyword : keywords) {
                map.computeIfAbsent(keyword, k -> EnumSet.noneOf(WidgetEnum.class)).add(widget);
            }
        }
        return Collections.unmodifiableMap(map);
    }

    private static Map<Widget.Type, EnumSet<SeleniumKeywordEnum>> createWidgetTypeToKeywordsMap() {
        return Arrays.stream(Widget.Type.values())
                .collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        Widgets::getRelevantSeleniumKeywords));
    }

    private static EnumSet<SeleniumKeywordEnum> getRelevantSeleniumKeywords(Widget.Type widgetType) {
        EnumSet<SeleniumKeywordEnum> keywords = getCommonKeywords();
        keywords.addAll(getSpecificKeywordsForWidgetType(widgetType));
        return keywords;
    }

    private static EnumSet<SeleniumKeywordEnum> getCommonKeywords() {
        return EnumSet.of(
                SeleniumKeywordEnum.ELEMENT_SHOULD_BE_ENABLED,
                SeleniumKeywordEnum.ELEMENT_SHOULD_BE_DISABLED,
                SeleniumKeywordEnum.ELEMENT_SHOULD_BE_VISIBLE,
                SeleniumKeywordEnum.ELEMENT_SHOULD_NOT_BE_VISIBLE,
                SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_IS_ENABLED,
                SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_IS_VISIBLE,
                SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_IS_NOT_VISIBLE,
                SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
    }

    private static EnumSet<SeleniumKeywordEnum> getSpecificKeywordsForWidgetType(Widget.Type widgetType) {
        var keywords = EnumSet.noneOf(SeleniumKeywordEnum.class);
        switch (widgetType) {
            case BUTTON:
                keywords.add(SeleniumKeywordEnum.CLICK_BUTTON);
                break;
            case CHECKBOX:
                keywords.add(SeleniumKeywordEnum.SELECT_CHECKBOX);
                keywords.add(SeleniumKeywordEnum.UNSELECT_CHECKBOX);
                keywords.add(SeleniumKeywordEnum.CHECK_BOX_SHOULD_BE_SELECTED);
                keywords.add(SeleniumKeywordEnum.CHECK_BOX_SHOULD_NOT_BE_SELECTED);
                break;
            case FILE_UPLOAD:
                keywords.add(SeleniumKeywordEnum.CHOOSE_FILE);
                break;
            case IMAGE:
                keywords.add(SeleniumKeywordEnum.CLICK_IMAGE);
                break;
            case LINK:
                keywords.add(SeleniumKeywordEnum.CLICK_LINK);
                break;
            case LIST:
                keywords.add(SeleniumKeywordEnum.SELECT_FROM_LIST_BY_LABEL);
                keywords.add(SeleniumKeywordEnum.UNSELECT_FROM_LIST_BY_LABEL);
                keywords.add(SeleniumKeywordEnum.SELECT_ALL_FROM_LIST);
                keywords.add(SeleniumKeywordEnum.UNSELECT_ALL_FROM_LIST);
                keywords.add(SeleniumKeywordEnum.LIST_SELECTION_SHOULD_BE);
                keywords.add(SeleniumKeywordEnum.LIST_SHOULD_HAVE_NO_SELECTIONS);
                break;
            case PASSWORD:
                keywords.add(SeleniumKeywordEnum.INPUT_PASSWORD);
                keywords.add(SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            case RADIO:
                keywords.add(SeleniumKeywordEnum.SELECT_RADIO_BUTTON);
                keywords.add(SeleniumKeywordEnum.RADIO_BUTTON_SHOULD_BE_SET_TO);
                keywords.add(SeleniumKeywordEnum.RADIO_BUTTON_SHOULD_NOT_BE_SELECTED);
                keywords.add(SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            case TEXT:
                keywords.add(SeleniumKeywordEnum.INPUT_TEXT);
                keywords.add(SeleniumKeywordEnum.TEXTFIELD_SHOULD_CONTAIN);
                keywords.add(SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            case TEXT_AREA:
                keywords.add(SeleniumKeywordEnum.INPUT_TEXT);
                keywords.add(SeleniumKeywordEnum.TEXTAREA_SHOULD_CONTAIN);
                keywords.add(SeleniumKeywordEnum.WAIT_UNTIL_ELEMENT_CONTAINS);
                break;
            default:
                break;
        }
        return keywords;
    }
}
