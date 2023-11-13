package net.kcww.app.robogen.translator.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Optional;

@Data
@Builder
public class KeywordModel {

    @NonNull
    private String keyword;
    private Optional<String> locator = Optional.empty();
    private Optional<String> value = Optional.empty();
}
