package br.edu.ufscar.backend.mealsfinder.models.states;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.AnalysisResult;

public abstract class EstablishmentState {
    public abstract void handleAnalysis(Establishment establishment, AnalysisResult result);
    public void checkRejectionExpiration(Establishment establishment) {
        System.out.println("Método para ser sobrescrito pelo estado Rejected.");
    };
}
