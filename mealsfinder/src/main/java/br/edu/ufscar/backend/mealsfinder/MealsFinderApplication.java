package br.edu.ufscar.backend.mealsfinder;

import br.edu.ufscar.backend.mealsfinder.models.entity.FoodTag;
import br.edu.ufscar.backend.mealsfinder.services.OtherFrameworkService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "br.edu.ufscar.backend.mealsfinder",
        "br.ufscar.pooa.Framework___POOA"
})
@EnableScheduling
public class MealsFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealsFinderApplication.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner testOtherFramework(OtherFrameworkService otherFrameworkService) {
        return args -> {
            System.out.println("INICIANDO TESTE DE INTEGRAÇÃO COM FRAMEWORK DE OUTRO GRUPO");
            var foodTagRepo = otherFrameworkService.getFoodTagRepository();

            System.out.println("\n[TESTE] Salvando uma nova FoodTag...");
            FoodTag novaTag = new FoodTag("Feito na Hora");

            foodTagRepo.save(novaTag);
            System.out.println(">> FoodTag salva com SUCESSO! ID gerado: " + novaTag.getId());

            System.out.println("\n[TESTE] Buscando a FoodTag com ID " + novaTag.getId() + "...");
            foodTagRepo.findById(novaTag.getId()).ifPresentOrElse(
                    tag -> System.out.println(">> FoodTag encontrada: " + tag),
                    () -> System.out.println(">> ERRO: Tag não encontrada!")
            );

            System.out.println("\n[TESTE] Listando todas as FoodTags salvas pelo framework do outro grupo...");
            foodTagRepo.findAll().forEach(tag -> System.out.println(">> - " + tag));
            System.out.println("TESTE DE INTEGRAÇÃO FINALIZADO");
        };
    }
}
