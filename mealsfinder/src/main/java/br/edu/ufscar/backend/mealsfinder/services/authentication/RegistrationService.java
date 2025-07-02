// Crie este novo serviço
package br.edu.ufscar.backend.mealsfinder.services.authentication;

import br.edu.ufscar.backend.mealsfinder.dtos.authentication.ClientRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.authentication.EstablishmentRegisterDTO;
import br.edu.ufscar.backend.mealsfinder.models.UserFactory;
import br.edu.ufscar.backend.mealsfinder.models.entity.Client;
import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.states.Pending;
import br.edu.ufscar.backend.mealsfinder.repositories.ClientRepository;
import br.edu.ufscar.backend.mealsfinder.repositories.EstablishmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements IRegistrationService {

    private final UserFactory userFactory;
    private final ClientRepository clientRepository;
    private final EstablishmentRepository establishmentRepository;

    public RegistrationService(UserFactory userFactory, ClientRepository clientRepository, EstablishmentRepository establishmentRepository) {
        this.userFactory = userFactory;
        this.clientRepository = clientRepository;
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    @Transactional
    public Client registerClient(ClientRegisterDTO clientRegisterDTO) {
        Client newClient = userFactory.createClient(clientRegisterDTO);
        return clientRepository.save(newClient);
    }

    @Override
    @Transactional
    public Establishment registerEstablishment(EstablishmentRegisterDTO establishmentRegisterDTO) {
        Establishment newEstablishment = userFactory.createEstablishment(establishmentRegisterDTO);

        return establishmentRepository.save(newEstablishment);
    }
}