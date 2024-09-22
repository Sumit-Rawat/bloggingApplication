package com.blog.blog_app_apis.controllers;

import com.blog.blog_app_apis.payloads.ApiResponse;
import com.blog.blog_app_apis.payloads.CategoryDto;
import com.blog.blog_app_apis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //GET - get category by id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId)
    {
        CategoryDto category = this.categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    //GET - get all categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categories = this.categoryService.getAllCategories();

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    //POST - create category
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto createdCategory=this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(categoryDto,HttpStatus.CREATED);
    }

    //PUT - update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);

    }

    //DELETE - delete a category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
    {
        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }


}
