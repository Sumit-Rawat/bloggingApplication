package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.payloads.PostResponse;
import com.blog.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    //GET - get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        PostDto post=this.postService.getPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    //GET - get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize
    )
    {
        PostResponse postDtoList=this.postService.getAllPost(pageNumber,pageSize);

        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    //POST - create a new post
    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId)
    {
        PostDto createdPost=this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //PUT - update a post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
    {
        PostDto updatedPost=this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //DELETE - delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    //GET - get post by category
    @GetMapping("/posts/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> postDtoList=this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    //GET - get post created by a user
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
        List<PostDto> postDtoList=this.postService.getPostByUser(userId);

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}
