package com.ths.restapi.controller;

import com.ths.restapi.dto.CategoryData;
import com.ths.restapi.dto.ResponseData;
import com.ths.restapi.dto.SearchData;
import com.ths.restapi.entity.Category;
import com.ths.restapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

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

    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findByName(
            @RequestBody SearchData searchData,
            @PathVariable("size") int size,
            @PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findByname(searchData.getSearchKey(),pageable);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Category> findByName(
            @RequestBody SearchData searchData,
            @PathVariable("size") int size,
            @PathVariable("page") int page,
            @PathVariable("sort") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if (sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryService.findByname(searchData.getSearchKey(),pageable);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories){
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        // merubah dari array ke iterable Arrays.asList(categories))
        responseData.setPayload(categoryService.saveBatch(Arrays.asList(categories)));
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }
}
