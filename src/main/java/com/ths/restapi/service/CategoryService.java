package com.ths.restapi.service;

import com.ths.restapi.entity.Category;
import com.ths.restapi.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.Optional;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        if (category.getId()!=null){
            Category current = categoryRepository.findById(category.getId()).get();
            current.setName(category.getName());
            category = current;
        }
        return categoryRepository.save(category);
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()){
            return null;
        }
        return  category.get();
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void removeOne(Long id){
        categoryRepository.deleteById(id);
    }

    public Iterable<Category> findByname(String name, Pageable pageable){
        return categoryRepository.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> categories){
        return categoryRepository.saveAll(categories);
    }

}
