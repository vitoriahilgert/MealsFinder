package br.edu.ufscar.backend.mealsfinder.models;

import br.edu.ufscar.backend.mealsfinder.models.enums.FoodTypesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Post extends Content {
    private List<String> pictureUrls;
    private List<FoodTypesEnum> tags;
    private List<UUID> commentIds;
    
    public Post() {
        super();
        this.pictureUrls = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.commentIds = new ArrayList<>();
    }
    
    public Post(UUID creatorId, String text) {
        super(creatorId, text);
        this.pictureUrls = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.commentIds = new ArrayList<>();
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
        return commentIds;
    }

    public void setCommentIds(List<UUID> commentIds) {
        this.commentIds = commentIds;
    }

    public void addComment(UUID commentId) {
        if (commentId != null && !commentIds.contains(commentId)) {
            commentIds.add(commentId);
        }
    }

    public boolean removeComment(UUID commentId) {
        return commentIds.remove(commentId);
    }

    public int getCommentCount() {
        return commentIds.size();
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
