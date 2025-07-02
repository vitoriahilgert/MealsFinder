package br.edu.ufscar.backend.mealsfinder.models.states;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.AnalysisResult;

import java.time.LocalDate;

public class Pending extends EstablishmentState{
    private static final Pending pendingInstance = new Pending();

    private Pending() {
    }

    public static Pending getInstance() {
        return pendingInstance;
    }

    @Override
    public void handleAnalysis(Establishment establishment, AnalysisResult result) {
        switch (result) {
            case ACCEPTED:
                System.out.println("Resultado da análise: Aprovado! Trocando de estado...");
                establishment.setVisible(true);
                establishment.setState(Accepted.getInstance());
                break;
            case REJECTED:
                System.out.println("Resultado da análise: Rejeitado! Trocando de estado...");
                establishment.setRejectionDate(LocalDate.now());
                establishment.setState(Rejected.getInstance());
                break;
        }
    }
}
