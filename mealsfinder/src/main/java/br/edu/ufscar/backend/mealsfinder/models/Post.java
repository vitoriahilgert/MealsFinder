package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Post extends Content {
    private List<String> pictureUrls;
    private List<FoodTypesEnum> tags;
    
    public Post() {
        super();
        this.pictureUrls = new ArrayList<>();
        this.tags = new ArrayList<>();
    }
    
    public Post(UUID creatorId, String text) {
        super(creatorId, text);
        this.pictureUrls = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public void addPicture(String pictureUrl) {
        if (pictureUrl != null && !pictureUrl.isEmpty() && !pictureUrls.contains(pictureUrl)) {
            pictureUrls.add(pictureUrl);
        }
    }

    public boolean removePicture(String pictureUrl) {
        return pictureUrls.remove(pictureUrl);
    }

    public List<FoodTypesEnum> getTags() {
        return tags;
    }

    public void setTags(List<FoodTypesEnum> tags) {
        this.tags = tags;
    }

    public void addTag(FoodTypesEnum tag) {
        if (tag != null && !tags.isEmpty() && !tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public boolean removeTag(FoodTypesEnum tag) {
        return tags.remove(tag);
    }

    public List<UUID> getCommentIds() {
        return contentRelationshipService.getCommentsByPost(this.getId());
    }

    public void addComment(UUID commentId) {
        if (!this.getCommentIds().contains(commentId))
            contentRelationshipService.addCommentToPost(this.getId(), commentId);
    }

    public void removeComment(UUID commentId) {
        if (this.getCommentIds().contains(commentId))
            contentRelationshipService.removeCommentFromPost(this.getId(), commentId);
    }

    public int getCommentCount() {
        return this.getCommentIds().size();
    }
    
    @Override
    public String getContentType() {
        return "POST";
    }
    
    @Override
    public String toString() {
        return "Post{" +
                "id=" + getId() +
                ", creator=" + getCreatorId() +
                ", pictures=" + pictureUrls.size() +
                ", tags=" + tags +
                ", comments=" + getCommentCount() +
                ", likes=" + getLikeCount() +
                '}';
    }
}
