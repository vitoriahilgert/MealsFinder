package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.*;
import java.util.UUID;

@Entity(name = "comment")
public class Comment {
    @Column(name = "id")
    private UUID id;

//    @Column(nullable = false)
//    private UUID postId;
//
//    @Column
//    private UUID parentCommentId;

    //private List<UUID> childCommentIds;


    public Comment() {
    }

    public Comment(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
