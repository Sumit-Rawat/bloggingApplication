package com.blog.blog_app_apis.repositories;

import com.blog.blog_app_apis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    //get user by Email

    Optional<User> findByEmail(String email);
}
