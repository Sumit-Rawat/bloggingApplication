package com.blog.blog_app_apis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotEmpty
    @Size(max=30, message = "Category title can be of max 30 characters")
    private String categoryName;

    @NotEmpty
    @Size(max=200, message = "Category description can be of max 200 characters")
    private String categoryDescription;
}
