package com.blog.blog_app_apis.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> results;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalElements;

    private Integer totalPages;

    private boolean isLastPage;

}
