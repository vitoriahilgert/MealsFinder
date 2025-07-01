package br.edu.ufscar.backend.mealsfinder.controllers;

import br.edu.ufscar.backend.mealsfinder.dtos.CreateTagRequest;
import br.edu.ufscar.backend.mealsfinder.models.entity.EnvironmentTag;
import br.edu.ufscar.backend.mealsfinder.models.entity.FoodTag;
import br.edu.ufscar.backend.mealsfinder.models.entity.ServiceTag;
import br.edu.ufscar.backend.mealsfinder.services.OtherFrameworkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final OtherFrameworkService otherFrameworkService;

    public TagController(OtherFrameworkService otherFrameworkService) {
        this.otherFrameworkService = otherFrameworkService;
    }

    @GetMapping("/food")
    public List<FoodTag> getAllFoodTags() {
        return otherFrameworkService.getFoodTagRepository().findAll();
    }

    @GetMapping("/food/{id}")
    public FoodTag getFoodTagById(@PathVariable Integer id) {
        return otherFrameworkService.getFoodTagRepository().findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FoodTag com ID " + id + " não encontrada."));
    }

    @PostMapping("/food")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodTag createFoodTag(@RequestBody CreateTagRequest request) {
        FoodTag newTag = new FoodTag();
        newTag.setName(request.name());
        return otherFrameworkService.getFoodTagRepository().save(newTag);
    }

    @PutMapping("/food/{id}")
    public FoodTag updateFoodTag(@PathVariable Integer id, @RequestBody CreateTagRequest request) {
        var foodTagRepo = otherFrameworkService.getFoodTagRepository();
        FoodTag existingTag = foodTagRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FoodTag com ID " + id + " não encontrada."));
        existingTag.setName(request.name());
        return foodTagRepo.update(existingTag);
    }

    @DeleteMapping("/food/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodTag(@PathVariable Integer id) {
        var foodTagRepo = otherFrameworkService.getFoodTagRepository();
        FoodTag tagToDelete = foodTagRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "FoodTag com ID " + id + " não encontrada."));
        foodTagRepo.delete(tagToDelete);
    }

    @GetMapping("/service")
    public List<ServiceTag> getAllServiceTags() {
        return otherFrameworkService.getServiceTagRepository().findAll();
    }

    @GetMapping("/service/{id}")
    public ServiceTag getServiceTagById(@PathVariable Integer id) {
        return otherFrameworkService.getServiceTagRepository().findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServiceTag com ID " + id + " não encontrada."));
    }

    @PostMapping("/service")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceTag createServiceTag(@RequestBody CreateTagRequest request) {
        ServiceTag newTag = new ServiceTag();
        newTag.setName(request.name());
        return otherFrameworkService.getServiceTagRepository().save(newTag);
    }

    @PutMapping("/service/{id}")
    public ServiceTag updateServiceTag(@PathVariable Integer id, @RequestBody CreateTagRequest request) {
        var serviceTagRepo = otherFrameworkService.getServiceTagRepository();
        ServiceTag existingTag = serviceTagRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServiceTag com ID " + id + " não encontrada."));
        existingTag.setName(request.name());
        return serviceTagRepo.update(existingTag);
    }

    @DeleteMapping("/service/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceTag(@PathVariable Integer id) {
        var serviceTagRepo = otherFrameworkService.getServiceTagRepository();
        ServiceTag tagToDelete = serviceTagRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServiceTag com ID " + id + " não encontrada."));
        serviceTagRepo.delete(tagToDelete);
    }

    @GetMapping("/environment")
    public List<EnvironmentTag> getAllEnvironmentTags() {
        return otherFrameworkService.getEnvironmentTagRepository().findAll();
    }

    @GetMapping("/environment/{id}")
    public EnvironmentTag getEnvironmentTagById(@PathVariable Integer id) {
        return otherFrameworkService.getEnvironmentTagRepository().findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EnvironmentTag com ID " + id + " não encontrada."));
    }

    @PostMapping("/environment")
    @ResponseStatus(HttpStatus.CREATED)
    public EnvironmentTag createEnvironmentTag(@RequestBody CreateTagRequest request) {
        EnvironmentTag newTag = new EnvironmentTag();
        newTag.setName(request.name());
        return otherFrameworkService.getEnvironmentTagRepository().save(newTag);
    }

    @PutMapping("/environment/{id}")
    public EnvironmentTag updateEnvironmentTag(@PathVariable Integer id, @RequestBody CreateTagRequest request) {
        var envTagRepo = otherFrameworkService.getEnvironmentTagRepository();
        EnvironmentTag existingTag = envTagRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EnvironmentTag com ID " + id + " não encontrada."));
        existingTag.setName(request.name());
        return envTagRepo.update(existingTag);
    }

    @DeleteMapping("/environment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnvironmentTag(@PathVariable Integer id) {
        var envTagRepo = otherFrameworkService.getEnvironmentTagRepository();
        EnvironmentTag tagToDelete = envTagRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EnvironmentTag com ID " + id + " não encontrada."));
        envTagRepo.delete(tagToDelete);
    }
}
