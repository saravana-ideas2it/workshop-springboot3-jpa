package com.ideas2it.workshop.entities.listeners;

import com.ideas2it.workshop.entities.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UserEntityListener {

    @PrePersist
    public void prePersist(User user) {
        user.setCreated(LocalDateTime.now());
        user.setCreatedBy("system");
    }

    @PreUpdate
    public void preUpdate(User user) {
        user.setUpdated(LocalDateTime.now());
        user.setUpdatedBy("system");
    }
}
