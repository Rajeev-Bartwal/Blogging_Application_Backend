package com.rajeev.Blogging_App.Services.Impl;

import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Model.Category;
import com.rajeev.Blogging_App.Payloads.CategoryDTO;
import com.rajeev.Blogging_App.Repository.CategoryRepo;
import com.rajeev.Blogging_App.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public  class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {

        Category cat = modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepo.save(cat);
        return modelMapper.map(addedCat , CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categorydto, Integer categoryId) {

            Category cat = categoryRepo.findById(categoryId).orElseThrow
                    (() -> new ResourceNotFoundException("Category" , "Id" , categoryId));

                cat.setCategoryTitle(categorydto.getCategoryTitle());
                cat.setCategoryDesc(categorydto.getCategoryDesc());
                Category updatedCat = categoryRepo.save(cat);

        return  modelMapper.map(updatedCat , CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow
                (() -> new ResourceNotFoundException("Category" , "Id" , categoryId));

        categoryRepo.delete(cat);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow
                (() -> new ResourceNotFoundException("Category" , "Id" , categoryId));

        return  modelMapper.map(cat , CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories =  categoryRepo.findAll();

        List<CategoryDTO> catDtos = categories.stream().map(cat -> modelMapper.map(cat , CategoryDTO.class)).toList();

        return catDtos;

    }
}
