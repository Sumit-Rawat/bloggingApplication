package com.blog.blog_app_apis.services.impl;

import com.blog.blog_app_apis.entities.Category;
import com.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.blog.blog_app_apis.payloads.CategoryDto;
import com.blog.blog_app_apis.repositories.CategoryRepo;
import com.blog.blog_app_apis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList=this.categoryRepo.findAll();

        List<CategoryDto> categoryDtoList=categoryList.stream().map(category->this.modelMapper.map(category,CategoryDto.class)).toList();
        return categoryDtoList;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);
        Category savedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));

        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory=this.categoryRepo.save(category);

        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(category);

    }


}
