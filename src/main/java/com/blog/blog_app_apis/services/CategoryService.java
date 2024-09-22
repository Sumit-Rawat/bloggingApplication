package com.blog.blog_app_apis.services;

import com.blog.blog_app_apis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //get category by id
    CategoryDto getCategoryById(Integer categoryId);

    //get all categories
    List<CategoryDto> getAllCategories();

    //create category
    CategoryDto createCategory(CategoryDto categoryDto);

    //update category
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete category
    void deleteCategory(Integer categoryId);

}
