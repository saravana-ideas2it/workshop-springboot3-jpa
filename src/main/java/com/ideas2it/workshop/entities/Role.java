package com.ideas2it.workshop.entities;


import com.ideas2it.workshop.entities.listeners.RoleEntityListener;
import com.ideas2it.workshop.entities.listeners.UserEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@EntityListeners(RoleEntityListener.class)

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;

    @Version
    @Column(name = "row_version")
    private Long rowVersion;
}
