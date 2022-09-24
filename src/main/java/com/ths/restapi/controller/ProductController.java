package com.ths.restapi.controller;

import com.ths.restapi.dto.ResponseData;
import com.ths.restapi.entity.Product;
import com.ths.restapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        // cek error (ada/tidak)
        if(errors.hasErrors()){
            // lakukan sesuatu
            for (ObjectError e: errors.getAllErrors()) {
                //System.err.println(errors.getAllErrors());
                responseData.getMessage().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            //throw new RuntimeException("Validation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.getMessage().add("Sukses");
        responseData.setPayload(productService.save(product));

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findAll(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){
        ResponseData responseData = new ResponseData<>();
        // cek error (ada/tidak)
        if(errors.hasErrors()){
            // lakukan sesuatu
            for (ObjectError e: errors.getAllErrors()) {
                       responseData.getMessage().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            //throw new RuntimeException("Validation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.getMessage().add("Sukses");
        responseData.setPayload(productService.save(product));

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.removeOne(id);
    }


}