package com.ideas2it.workshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ideas2it.workshop.entities.listeners.UserEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(UserEntityListener.class)
@Entity
@Table(name = "users")
public class User implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Basic
	@Column(name = "name", nullable = false, length = 250)
	private String name;
	@Basic
	@Column(name = "email", nullable = false, length = 250)
	private String email;
	@Basic
	@Column(name = "phone", nullable = false, length = 250)
	private String phone;
	@Basic
	@Column(name = "password", nullable = false, length = 250)
	private String password;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

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
