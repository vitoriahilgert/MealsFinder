package br.edu.ufscar.backend.mealsfinder.models.entity;

import br.edu.ufscar.backend.mealsfinder.models.key.BlockedUserId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "blocked_users")
public class BlockedUser {

    @EmbeddedId
    private BlockedUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("blockerId")
    @JoinColumn(name = "blocker_id")
    private Client blocker;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("blockedId")
    @JoinColumn(name = "blocked_id")
    private User blocked;

    public BlockedUser() {
    }

    public BlockedUser(Client blocker, User blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
        this.id = new BlockedUserId(blocker.getId(), blocked.getId());
    }

    public BlockedUserId getId() {
        return id;
    }

    public void setId(BlockedUserId id) {
        this.id = id;
    }

    public Client getBlocker() {
        return blocker;
    }

    public void setBlocker(Client blocker) {
        this.blocker = blocker;
    }

    public User getBlocked() {
        return blocked;
    }

    public void setBlocked(User blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedUser that = (BlockedUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
