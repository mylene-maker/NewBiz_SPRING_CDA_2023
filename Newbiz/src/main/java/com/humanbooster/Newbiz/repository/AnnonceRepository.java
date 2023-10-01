package com.humanbooster.Newbiz.repository;

import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends CrudRepository<Annonce, Long> {

    public List<Annonce> findAll();

    List<Annonce> findByTitre(String name);

    @Query("SELECT a FROM Annonce a WHERE a.category.id = :categoryId")
    List<Annonce> findByCategoryId(@Param("categoryId") Long categoryId);
}
