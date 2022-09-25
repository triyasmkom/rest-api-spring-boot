package com.ths.restapi.repo;

import com.ths.restapi.entity.Product;
import com.ths.restapi.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;

@org.springframework.stereotype.Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.name=:name")
    Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);



}
