package com.blog3.blog3.repository;

import com.blog3.blog3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.*;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
