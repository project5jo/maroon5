package com.spring.mood.projectmvc.repository;

import com.spring.mood.projectmvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByuserName(String userName);
}
