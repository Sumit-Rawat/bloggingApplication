package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.config.AppConstants;
import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.payloads.PostResponse;
import com.blog.blog_app_apis.services.FileService;
import com.blog.blog_app_apis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;


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
            @RequestParam(name = "pageNumber", value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    )
    {
        PostResponse postDtoList=this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);

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

    //GET - get post by title
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostByTitle(@PathVariable String keyword)
    {
        List<PostDto> postDtoList = this.postService.searchPost(keyword);

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    //POST - upload post image
    @PostMapping("/post/image/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam MultipartFile file,
                                                   @PathVariable Integer postId) throws IOException {

        //get the post by post id if exist else throw resource not found
        PostDto postById = this.postService.getPostById(postId);

        //upload image get the name of the uploaded image
        String fileName = this.fileService.uploadImage(path, file);
        System.out.println(fileName);
        //set image  name in post dto
        postById.setImage(fileName);
        System.out.println(postById);
        //update the post in the db
        PostDto postDto = this.postService.updatePost(postById, postId);

        return new ResponseEntity<>(postDto,HttpStatus.OK);


    }

    // method to serve files
    @GetMapping(value="/post/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
