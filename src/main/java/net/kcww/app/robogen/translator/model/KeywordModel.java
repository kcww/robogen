package net.kcww.app.robogen.translator.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Builder
@Accessors(fluent = true)
public class KeywordModel {

    @NonNull
    private String keyword;
    private String locator;
    private String value;
}
