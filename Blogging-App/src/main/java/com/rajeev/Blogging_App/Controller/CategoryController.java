package com.rajeev.Blogging_App.Controller;


import com.rajeev.Blogging_App.Model.Category;
import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.CategoryDTO;
import com.rajeev.Blogging_App.Services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category API's")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    @Operation(summary = "Creating a category (Only Admin Can create a Category)")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categorydto){
        return new ResponseEntity<>(categoryService.createCategory(categorydto) , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}")
    @Operation(summary = "Only Admin can update the Category")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto,@PathVariable Integer categoryId){
        return  new ResponseEntity<>(categoryService.updateCategory(categoryDto ,categoryId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Only admin can delete the category")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully" , true),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "For getting a particular category by Id")
    public  ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId),HttpStatus.FOUND);
    }

    @GetMapping("/")
    @Operation(summary = "For getting All categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories() , HttpStatus.OK);
    }

}
