package com.blog.blog_app_apis.repositories;

import com.blog.blog_app_apis.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}