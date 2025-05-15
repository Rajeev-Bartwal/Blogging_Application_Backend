package com.rajeev.Blogging_App.Services;

import com.rajeev.Blogging_App.Payloads.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDto);

    CategoryDTO updateCategory(CategoryDTO categorydto, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDTO getCategory(Integer categoryId);

    List<CategoryDTO> getAllCategories();

}
