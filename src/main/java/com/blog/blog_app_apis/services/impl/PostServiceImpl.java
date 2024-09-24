package com.blog.blog_app_apis.services.impl;

import com.blog.blog_app_apis.entities.Category;
import com.blog.blog_app_apis.entities.Post;
import com.blog.blog_app_apis.entities.User;
import com.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_app_apis.payloads.PostDto;
import com.blog.blog_app_apis.payloads.PostResponse;
import com.blog.blog_app_apis.repositories.CategoryRepo;
import com.blog.blog_app_apis.repositories.PostRepo;
import com.blog.blog_app_apis.repositories.UserRepo;
import com.blog.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));

        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);

        post.setPostDate(new Date());
        post.setUser(user); //set user
        post.setCategory(category); //set category

        Post createdPost = this.postRepo.save(post);
        return this.modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable p= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)); // getting pageable object

        Page<Post> postPage=this.postRepo.findAll(p); // get the page object by passing pageable object in findAll

        List<Post> content = postPage.getContent(); // to get all post on that page

        List<PostDto> postDtoList = content.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).toList();

        PostResponse response=new PostResponse();

        response.setResults(postDtoList);
        response.setPageNumber(postPage.getNumber());
        response.setPageSize(postPage.getSize());
        response.setTotalElements(postPage.getNumberOfElements());
        response.setTotalPages(postPage.getTotalPages());
        response.setLastPage(postPage.isLast());


        return response;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatePost=this.postRepo.save(post);

        return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post=this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        //get the category by category id and if category exists then get post for that category
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        //get post of above category
        List<Post> postList=this.postRepo.findByCategory(category);

        List<PostDto> postDtoList=postList.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).toList();

        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        //get the user by user id and if user exists the get the post created by that user
        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));

        //get the post made by above user

        List<Post> postList=this.postRepo.findByUser(user);

        List<PostDto> postDtoList=postList.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).toList();

        return postDtoList;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return List.of();
    }
}
