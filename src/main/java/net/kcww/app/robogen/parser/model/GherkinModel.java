package net.kcww.app.robogen.parser.model;

import io.cucumber.messages.types.GherkinDocument;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Accessors;

import java.util.SortedSet;

@Data
@Builder
@Accessors(fluent = true)
public class GherkinModel {

    private final GherkinDocument document;

    // Feature: Booking A Hotel Room
    //    As a user, I want to book a hotel room, so that I can stay in the hotel.
    //
    //    Scenario Outline: User books a hotel room
    private final String featureName;
    private final String featureDescription;
    private final String scenarioName;

    @Singular
    private final SortedSet<ScenarioStepModel> scenarioSteps;
}
