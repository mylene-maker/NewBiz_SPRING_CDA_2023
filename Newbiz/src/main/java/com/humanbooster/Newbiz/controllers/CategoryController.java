package com.humanbooster.Newbiz.controllers;

import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/annonces-par-categorie/{categoryId}")
    public String annoncesParCategorie(@PathVariable Long categoryId, Model model) {
        List<Annonce> annonces = annonceService.getAnnoncesByCategoryId(categoryId);
        model.addAttribute("annonces", annonces);
        return "annoncesByCategory";
    }

}
