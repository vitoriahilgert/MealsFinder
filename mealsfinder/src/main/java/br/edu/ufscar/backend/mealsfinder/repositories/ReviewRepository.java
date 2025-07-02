package br.edu.ufscar.backend.mealsfinder.repositories;

import br.edu.ufscar.backend.mealsfinder.models.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByReviewedEstablishmentId(String establishmentId);
}
