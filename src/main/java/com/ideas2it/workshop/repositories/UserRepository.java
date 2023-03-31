package com.ideas2it.workshop.repositories;

import com.ideas2it.workshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

