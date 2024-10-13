package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.payloads.CommentDto;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
