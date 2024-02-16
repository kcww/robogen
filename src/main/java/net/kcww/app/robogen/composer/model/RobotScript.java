package net.kcww.app.robogen.composer.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import net.kcww.app.robogen.translator.model.Keyword;

import java.util.List;

@Data
@Builder
public class RobotScript {

    @NonNull
    private String url;

    @NonNull
    private String featureName;

    @NonNull
    private String featureDescription;

    @NonNull
    private String scenarioName;

    @NonNull
    private List<Keyword> keywords;
}
