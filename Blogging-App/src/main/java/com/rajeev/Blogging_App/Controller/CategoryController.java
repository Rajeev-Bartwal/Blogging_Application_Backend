package com.rajeev.Blogging_App.Controller;


import com.rajeev.Blogging_App.Model.Category;
import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.CategoryDTO;
import com.rajeev.Blogging_App.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categorydto){
        return new ResponseEntity<>(categoryService.createCategory(categorydto) , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto,@PathVariable Integer categoryId){
        return  new ResponseEntity<>(categoryService.updateCategory(categoryDto ,categoryId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully" , true),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId),HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories() , HttpStatus.OK);
    }

}
