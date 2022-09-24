package com.ths.restapi.service;

import com.ths.restapi.entity.Product;
import com.ths.restapi.entity.Supplier;
import com.ths.restapi.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired SupplierService supplierService;

    public Product save(@Valid Product product){
        return productRepository.save(product);
    }

    public Product findOne(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public void removeOne(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findByName(String name){
        return productRepository.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);
        if (product==null){
            throw new RuntimeException("Product with Id: "+productId+" not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public Product findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> findProductByNameLike(String name){
        return productRepository.findProductByNameLike("%"+name+"%");
    }

    public List<Product> findProductByCategory(Long categoryId){
        return productRepository.findProductByCategory(categoryId);
    }

    public List<Product> findProductBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);
        if (supplier==null){
            return new ArrayList<Product>();
        }
        return productRepository.findProductBySupplier(supplier);
    }
}
