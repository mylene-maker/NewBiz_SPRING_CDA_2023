package com.humanbooster.Newbiz.repository;

import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    List<Category> findAll();

    List<Category> findByName(String name);


}
