package br.edu.ufscar.backend.mealsfinder.services.review;

import br.edu.ufscar.backend.mealsfinder.dtos.review.CreateReviewRequestDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.*;
import br.edu.ufscar.backend.mealsfinder.models.states.Accepted;
import br.edu.ufscar.backend.mealsfinder.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Review createReview(CreateReviewRequestDTO dto) {
        Establishment establishment = establishmentRepository.findById(dto.getEstablishmentId())
                .orElseThrow(() -> new NoSuchElementException("Estabelecimento com ID " + dto.getEstablishmentId() + " não encontrado."));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Cliente com ID " + dto.getEstablishmentId() + " não encontrado."));

        if (!(establishment.getState() instanceof Accepted)) {
            throw new IllegalStateException("Não é possível avaliar um estabelecimento que não está com o status 'ACEITO'.");
        }

        UUID sharedId = UUID.randomUUID();
        Post newPost = new Post();
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setDescription(dto.getDescription());
        newPost.setUser(client);

        Review newReview = new Review();
        newReview.setId(UUID.randomUUID().toString());
        newReview.setReviewedEstablishment(establishment);
        newReview.setPriceRate(dto.getPriceRate());
        newReview.setFoodRate(dto.getFoodRate());
        newReview.setEstablishmentRate(dto.getEstablishmentRate());
        newReview.setServiceRate(dto.getServiceRate());
        newReview.setDeliveryRate(dto.getDeliveryRate());
        newReview.setDeliveryReview(dto.isDeliveryReview());

        newReview.setPost(newPost);
        newPost.setReview(newReview);

        postRepository.save(newPost);

        return newReview;
    }

//    public List<Review> getAllByEstablishment(String establishmentId) {
//        // Apenas delega a chamada para o método customizado do repositório
//        return reviewRepository.findByEstablishment(establishmentId);
//    }
}