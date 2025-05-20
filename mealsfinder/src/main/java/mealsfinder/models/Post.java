package mealsfinder.models;

import mealsfinder.models.enums.EstablishmentTagsEnum;
import mealsfinder.models.enums.FoodTypesEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class Post extends Content {
    private List<String> pictureUrls;
    private List<FoodTypesEnum> foodTags;
    private List<EstablishmentTagsEnum> establishmentTags;
    private List<UUID> commentIds;
    
    public Post() {
        super();
        this.pictureUrls = new ArrayList<>();
        this.foodTags = new ArrayList<>();
        this.commentIds = new ArrayList<>();
    }
    
    public Post(UUID creatorId, String text) {
        super(creatorId, text);
        this.pictureUrls = new ArrayList<>();
        this.foodTags = new ArrayList<>();
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

    public List<FoodTypesEnum> getFoodTags() {
        return foodTags;
    }

    public void setFoodTags(List<FoodTypesEnum> tags) {
        this.foodTags = tags;
    }

    public void addFoodTag(FoodTypesEnum tag) {
        if (tag != null && !foodTags.isEmpty() && !foodTags.contains(tag)) {
            foodTags.add(tag);
        }
    }

    public List<EstablishmentTagsEnum> getEstablishmentTags() {
        return establishmentTags;
    }

    public void setEstablishmentTags(List<EstablishmentTagsEnum> tags) {
        this.establishmentTags = tags;
    }

    public void addEstablishmentTag(EstablishmentTagsEnum tag) {
        if (tag != null && !establishmentTags.isEmpty() && !establishmentTags.contains(tag)) {
            establishmentTags.add(tag);
        }
    }

    public boolean removeTag(FoodTypesEnum tag) {
        return foodTags.remove(tag);
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
                ", foodTags=" + foodTags +
                ", establishmentTags=" + establishmentTags +
                ", comments=" + getCommentCount() +
                ", likes=" + getLikeCount() +
                '}';
    }
}