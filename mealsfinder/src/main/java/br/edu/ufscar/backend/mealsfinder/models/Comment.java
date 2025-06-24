package br.edu.ufscar.backend.mealsfinder.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "comments")
@DiscriminatorValue("COMMENT")
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Content {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
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
