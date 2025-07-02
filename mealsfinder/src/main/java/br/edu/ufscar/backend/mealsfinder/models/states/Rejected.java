package br.edu.ufscar.backend.mealsfinder.models.states;

import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.AnalysisResult;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rejected extends EstablishmentState {
    private static final Rejected rejectedInstance = new Rejected();

    private Rejected() {
    }

    public static Rejected getInstance() {
        return rejectedInstance;
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
                System.out.println("Resultado da análise: Rejeitado pela segunda vez! Trocando de estado...");
                establishment.setState(Banned.getInstance());
                break;
        }
    }

    @Override
    public void checkRejectionExpiration(Establishment establishment) {
        LocalDate rejectionTime = establishment.getRejectionDate();
        if (rejectionTime == null) return;

        long daysSinceRejection = ChronoUnit.DAYS.between(rejectionTime, LocalDate.now());
        if (daysSinceRejection >= 30) {
            System.out.println("Prazo de contestação expirou para " + establishment.getName() + ". Banindo...");
            establishment.setState(Banned.getInstance());
        }
    }
}