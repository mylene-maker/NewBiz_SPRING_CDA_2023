package com.humanbooster.Newbiz.services;

import com.humanbooster.Newbiz.models.Category;
import com.humanbooster.Newbiz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAll(){
        return this.categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
