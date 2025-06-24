package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.framework.retentions.Column;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Entity;
import br.edu.ufscar.backend.mealsfinder.framework.retentions.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;

@Entity(tableName = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Content {

    @Column(name = "post_id")
    private Post post;

    public Comment(User creator, String text, Post post) {
        super(creator, text);
        this.post = post;
    }

    public Comment(User creator, String text, Post post, Comment parentComment) {
        super(creator, text);
        this.post = post;
    }

    @Override
    public String getContentType() {
        return "COMMENT";
    }
}
