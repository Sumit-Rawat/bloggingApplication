package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.CommentDto;
import com.blog.blog_app_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //POST - add new comment
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> addNewComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto addedCommentDto = this.commentService.addComment(commentDto, postId);

        return new ResponseEntity<>(addedCommentDto, HttpStatus.CREATED);
    }

    //Delete - delete comment by id
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }

}
