package com.blog.blog_app_apis.repositories;

import com.blog.blog_app_apis.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
