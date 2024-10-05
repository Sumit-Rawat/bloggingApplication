package com.blog.blog_app_apis.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDto {

    private Integer id;

    @NotEmpty
    @Size(max=100, message = "Title can be of max 100 characters")
    private String title;

    @NotEmpty
    private String content;

    private String image;

    @NotEmpty
    private Date postDate;

    @NotEmpty
    private UserDto user;

    @NotEmpty
    private CategoryDto category;

}
