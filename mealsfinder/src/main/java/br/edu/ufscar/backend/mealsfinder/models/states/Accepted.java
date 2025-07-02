package br.edu.ufscar.backend.mealsfinder.models.states;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.AnalysisResult;

public class Accepted extends EstablishmentState {
    private static final Accepted acceptedInstance = new Accepted();

    private Accepted() {
    }

    public static Accepted getInstance() {
        return acceptedInstance;
    }

    @Override
    public void handleAnalysis(Establishment establishment, AnalysisResult result) {
        establishment.setVisible(true);
    }
}
