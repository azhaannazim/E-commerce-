package com.ecommerce.project.sbECom.controller;


import com.ecommerce.project.sbECom.model.Category;
import com.ecommerce.project.sbECom.payload.CategoryDTO;
import com.ecommerce.project.sbECom.payload.CategoryResponse;
import com.ecommerce.project.sbECom.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api") //same word like api here can be defined here
public class CategoryController {
    @Autowired //field injection
    private CategoryService service;

//    public CategoryController(CategoryService service) { // constructor injection
//        this.service = service;
//    }

    @GetMapping("public/categories")
    //@RequestMapping(value = "api/public/categories" , method = RequestMethod.GET)
    //it can be used universally
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name = "pageNumber") Integer pageNumber,
            @RequestParam(name = "pageSize") Integer pageSize){
        return new ResponseEntity<>(service.getAllCategories(pageNumber , pageSize) , HttpStatus.OK);
    }

    @PostMapping("public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedcategoryDTO = service.createCategory(categoryDTO);
        return ResponseEntity.ok(savedcategoryDTO);
    }

    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
            CategoryDTO categoryDTO = service.deleteCategory(categoryId);
            return new ResponseEntity<>(categoryDTO , HttpStatus.OK);
            //return ResponseEntity.status(HttpStatus.OK).body(status);
            //return ResponseEntity.ok(status);
    }

    @PutMapping("public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO ,@PathVariable Long categoryId){

            CategoryDTO  updateCategoryDTO = service.updateCategory(categoryDTO , categoryId);
            return ResponseEntity.ok(updateCategoryDTO);
    }


}
