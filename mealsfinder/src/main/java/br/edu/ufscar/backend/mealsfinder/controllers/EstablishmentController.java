package br.edu.ufscar.backend.mealsfinder.controllers;

import br.edu.ufscar.backend.mealsfinder.dtos.establishment.EstablishmentResponseDTO;
import br.edu.ufscar.backend.mealsfinder.dtos.establishment.EstablishmentUpdateDTO;
import br.edu.ufscar.backend.mealsfinder.models.entity.Establishment;
import br.edu.ufscar.backend.mealsfinder.models.enums.AnalysisResult;
import br.edu.ufscar.backend.mealsfinder.services.establishment.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public ResponseEntity<List<Establishment>> getAllEstablishments() {
        List<Establishment> establishments = establishmentService.findAll();
        return ResponseEntity.ok(establishments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Establishment> updateEstablishment(
            @PathVariable String id,
            @RequestBody EstablishmentUpdateDTO updateDTO
    ) {
        Establishment updated = establishmentService.updateEstablishmentProfile(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/analyze/{id}")
    public ResponseEntity<EstablishmentResponseDTO> analyzeEstablishment(
            @PathVariable String id,
            @RequestParam("result") AnalysisResult result) {

        EstablishmentResponseDTO updatedEstablishment = establishmentService.analyzeEstablishment(id, result);
        return ResponseEntity.ok(updatedEstablishment);
    }


}
