package com.humanbooster.Newbiz.services;

import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.models.Category;
import com.humanbooster.Newbiz.repository.AnnonceRepository;
import com.humanbooster.Newbiz.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Annonce> getAllAnnonces(){
        return this.annonceRepository.findAll();
    }


    public List<Annonce> findByName(String name) {
        return annonceRepository.findByTitre(name);
    }
    public void save(Annonce annonce) {
        annonceRepository.save(annonce);
    }

//    public void delete(Annonce annonce){
//        this.annonceRepository.delete(annonce);
//    }

    public List<Annonce> getAnnoncesByCategoryId(Long categoryId) {
        return annonceRepository.findByCategoryId(categoryId);
    }

    @Transactional
    public void delete(Long annonceId) {
        // Récupérer l'annonce par son ID
        Annonce annonce = annonceRepository.findById(annonceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce inexistante"));

        // Récupérer la catégorie associée à l'annonce
        Category category = annonce.getCategory();

        // Supprimer l'annonce
        annonceRepository.delete(annonce);

        // Vérifier si la catégorie n'est associée à aucune autre annonce
        if (category != null && category.getName_category().isEmpty()) {
            // Si aucune autre annonce n'est liée à cette catégorie, supprimer la catégorie
            categoryRepository.delete(category);
        }
    }

}
