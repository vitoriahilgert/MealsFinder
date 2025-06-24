package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import br.edu.ufscar.backend.mealsfinder.models.enums.ImageType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity(tableName = "images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Column(name = "id")
    private UUID id;
    @Column(name = "url")
    private String url;
    @Column(name = "type")
    private ImageType type;

    @Column(name = "establishment_id")
    private Establishment establishment;
}
