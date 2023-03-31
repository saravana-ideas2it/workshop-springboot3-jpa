package com.ideas2it.workshop.entities.listeners;

import com.ideas2it.workshop.entities.Role;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class RoleEntityListener {

    @PrePersist
    public void prePersist(Role role) {
        role.setCreated(LocalDateTime.now());
        role.setCreatedBy("system");
    }

    @PreUpdate
    public void preUpdate(Role role) {
        role.setUpdated(LocalDateTime.now());
        role.setUpdatedBy("system");
    }
}
