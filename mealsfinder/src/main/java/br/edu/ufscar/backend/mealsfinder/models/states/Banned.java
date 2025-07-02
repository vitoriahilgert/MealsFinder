package br.edu.ufscar.backend.mealsfinder.models.states;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.AnalysisResult;

public class Banned extends EstablishmentState {
    private static final Banned bannedInstance = new Banned();

    private Banned() {
    }

    public static Banned getInstance() {
        return bannedInstance;
    }

    @Override
    public void handleAnalysis(Establishment establishment, AnalysisResult result) {

    }
}
