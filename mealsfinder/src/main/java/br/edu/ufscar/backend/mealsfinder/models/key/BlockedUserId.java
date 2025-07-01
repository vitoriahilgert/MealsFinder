package br.edu.ufscar.backend.mealsfinder.models.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BlockedUserId implements Serializable {

    @Column(name = "blocker_id")
    private String blockerId;

    @Column(name = "blocked_id")
    private String blockedId;

    public BlockedUserId() {
    }

    public BlockedUserId(String blockerId, String blockedId) {
        this.blockerId = blockerId;
        this.blockedId = blockedId;
    }

    public String getBlockerId() {
        return blockerId;
    }

    public void setBlockerId(String blockerId) {
        this.blockerId = blockerId;
    }

    public String getBlockedId() {
        return blockedId;
    }

    public void setBlockedId(String blockedId) {
        this.blockedId = blockedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedUserId that = (BlockedUserId) o;
        return Objects.equals(blockerId, that.blockerId) &&
                Objects.equals(blockedId, that.blockedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockerId, blockedId);
    }
}
