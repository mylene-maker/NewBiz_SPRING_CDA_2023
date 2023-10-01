package com.humanbooster.Newbiz.controllers;

import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.repository.AnnonceRepository;
import com.humanbooster.Newbiz.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private AnnonceService annonceService;


    @RequestMapping("")
    ModelAndView home(){
        ModelAndView mv = new ModelAndView("list");
        List<Annonce> annonces = annonceService.getAllAnnonces();

        mv.addObject("annonces", annonces);
        return mv;
    }


}
