package com.ths.restapi.controller;

import com.ths.restapi.dto.CategoryData;
import com.ths.restapi.dto.ResponseData;
import com.ths.restapi.entity.Category;
import com.ths.restapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired private CategoryService categoryService;
    @Autowired private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(
            @Valid
            @RequestBody CategoryData categoryData,
            Errors errors
            ){
        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error);
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @PutMapping
    @PostMapping
    public ResponseEntity<ResponseData<Category>> update(
            @Valid
            @RequestBody CategoryData categoryData,
            Errors errors
    ){
        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error);
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(category);
        return ResponseEntity.ok(responseData);
    }
}
