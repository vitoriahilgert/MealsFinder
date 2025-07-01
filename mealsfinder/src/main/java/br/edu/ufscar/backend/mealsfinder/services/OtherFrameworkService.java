package br.edu.ufscar.backend.mealsfinder.services;

import br.edu.ufscar.backend.mealsfinder.models.entity.EnvironmentTag;
import br.edu.ufscar.backend.mealsfinder.models.entity.FoodTag;
import br.edu.ufscar.backend.mealsfinder.models.entity.ServiceTag;
import br.ufscar.pooa.Framework___POOA.FrameworkRepositoryFactory;
import br.ufscar.pooa.Framework___POOA.persistence_framework.IFrameworkRepository;
import br.ufscar.pooa.Framework___POOA.persistence_framework.database.DatabaseManager;
import org.springframework.stereotype.Service;

@Service
public class LegacyFrameworkService {

    private final IFrameworkRepository<FoodTag, Integer> foodTagRepository;
    private final IFrameworkRepository<ServiceTag, Integer> serviceTagRepository;
    private final IFrameworkRepository<EnvironmentTag, Integer> environmentTagRepository;

    public LegacyFrameworkService(DatabaseManager databaseManager) {
        System.out.println("-> Inicializando serviço com o framework legado...");

        var foodTagFactory = new FrameworkRepositoryFactory<FoodTag, Integer>(databaseManager);
        this.foodTagRepository = foodTagFactory.getRepository(FoodTag.class);

        var serviceTagFactory = new FrameworkRepositoryFactory<ServiceTag, Integer>(databaseManager);
        this.serviceTagRepository = serviceTagFactory.getRepository(ServiceTag.class);

        var environmentTagFactory = new FrameworkRepositoryFactory<EnvironmentTag, Integer>(databaseManager);
        this.environmentTagRepository = environmentTagFactory.getRepository(EnvironmentTag.class);

        System.out.println("-> Repositórios do framework legado prontos para uso.");
    }

    public IFrameworkRepository<FoodTag, Integer> getFoodTagRepository() {
        return foodTagRepository;
    }

    public IFrameworkRepository<ServiceTag, Integer> getServiceTagRepository() {
        return serviceTagRepository;
    }

    public IFrameworkRepository<EnvironmentTag, Integer> getEnvironmentTagRepository() {
        return environmentTagRepository;
    }
}
