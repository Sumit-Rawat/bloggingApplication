package com.blog.blog_app_apis.services.impl;

import com.blog.blog_app_apis.entities.Comment;
import com.blog.blog_app_apis.entities.Post;
import com.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_app_apis.payloads.CommentDto;
import com.blog.blog_app_apis.repositories.CommentRepo;
import com.blog.blog_app_apis.repositories.PostRepo;
import com.blog.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId) {
        // get the post with id to which the comment is made
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        //if post exist then add comment
        // get comment from commentDto then set post in that comment and save to db.
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment addedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(addedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

        this.commentRepo.delete(comment);

    }
}
