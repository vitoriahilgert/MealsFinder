package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.services.contentrelationship.ContentRelationshipService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

abstract class Content {
    private UUID id;
    private String text;
    private UUID creatorId;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;

    protected ContentRelationshipService contentRelationshipService;

    public Content() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = this.creationDate;
    }

    public Content(UUID creatorId, String text) {
        this();
        this.creatorId = creatorId;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public void setContentRelationshipService(ContentRelationshipService contentService) {
        this.contentRelationshipService = contentService;
    }

    public List<UUID> getLikes() {
        return contentRelationshipService.getContentLikes(this.getId());
    }

    public int getLikeCount() {
        return contentRelationshipService.getContentLikeCount(this.getId());
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public void addLike(UUID userId) {
        if (!contentRelationshipService.isContentLikedByUser(this.getId(),userId))
            contentRelationshipService.likeContent(this.getId(), userId);
    }

    public void removeLike(UUID userId) {
        if (contentRelationshipService.isContentLikedByUser(this.getId(),userId))
            contentRelationshipService.unlikeContent(this.getId(), userId);
    }

    public boolean isLikedBy(UUID userId) {
        return contentRelationshipService.isContentLikedByUser(this.getId(), userId);
    }

    public abstract String getContentType();
    
    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", likes=" + getLikeCount() +
                ", created=" + creationDate +
                '}';
    }
}
