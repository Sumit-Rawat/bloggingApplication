package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //get post by id
    PostDto getPostById(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //update post
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post of a user
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<PostDto> searchPost(String keyword);
}
