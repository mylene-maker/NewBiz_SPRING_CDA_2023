package com.humanbooster.Newbiz.controllers;

import com.humanbooster.Newbiz.exception.WrongFileTypeException;
import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.models.Category;
import com.humanbooster.Newbiz.repository.AnnonceRepository;
import com.humanbooster.Newbiz.repository.CategoryRepository;
import com.humanbooster.Newbiz.services.AnnonceService;
import com.humanbooster.Newbiz.services.CategoryService;
import com.humanbooster.Newbiz.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/annonce")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    StorageService storageService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AnnonceRepository annonceRepository;


    @RequestMapping("/list/{annonce}")
    public ModelAndView getOne(@PathVariable(required = false) Annonce annonce){

        if(annonce == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce inexistante !");
        }

        ModelAndView mv = new ModelAndView("one");
        mv.addObject("annonces", annonce);

        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable(required = false) Category category) {
        Annonce annonce = new Annonce();
        annonce.setCategory(category);

        ModelAndView mv = new ModelAndView("add");
        mv.addObject("annonce", annonce);
        mv.addObject("categories", categoryRepository.findAll());
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String formAdd( @Valid Annonce annonce,
                          BindingResult bindingResult,
//                          @RequestParam("image") MultipartFile image,
                          Model model) throws IOException, WrongFileTypeException {

        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("annonce", annonce);
            return "add";
        } else {

//            annonce.setImage(storageService.store(image));
            annonce.setDateDePublication(new Date());
            this.annonceRepository.save(annonce);
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delete/{annonce}", method = RequestMethod.GET)
    public String delete(@PathVariable Long annonce){

        this.annonceService.delete(annonce);
        return "redirect:/";
    }

    @RequestMapping(value = "edit/{annonce}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(required = false) Annonce annonce){
        if (annonce == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce inexistante");

        }
        ModelAndView mv = new ModelAndView("add");
        mv.addObject("annonce", annonce);
        return mv;
    }

    @RequestMapping(value = "edit/{annonce}", method = RequestMethod.POST)
    public String editSubmit(@Valid Annonce annonce, BindingResult bindingResult){
        if (annonce == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce inexistante");

        }
        if (bindingResult.hasErrors()){
            return "add";
        }else {
            this.annonceService.save(annonce);
            return "redirect:/";
        }
    }

}
