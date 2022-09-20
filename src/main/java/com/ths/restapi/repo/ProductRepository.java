package com.ths.restapi.repo;

import com.ths.restapi.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findByNameContains(String name);
}
