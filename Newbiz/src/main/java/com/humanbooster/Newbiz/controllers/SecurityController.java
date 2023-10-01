package com.humanbooster.Newbiz.controllers;

import com.humanbooster.Newbiz.groupvalidation.RegisterGroup;
import com.humanbooster.Newbiz.models.User;
import com.humanbooster.Newbiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class SecurityController {

    @Autowired
    UserService userService;

//    login et logout

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    ModelAndView login(){
        ModelAndView mv = new ModelAndView("security/login");
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    ModelAndView logout(){
        ModelAndView mv = new ModelAndView("security/logout");
        return mv;
    }

   // Register

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    ModelAndView register(){
        User user = new User();
        ModelAndView mv = new ModelAndView("security/register");
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    String registerSubmitted(@Validated(RegisterGroup.class) User user, BindingResult bindingResult, Model model){
        User userExist =  this.userService.findByUsername(user.getUsername());

        if (bindingResult.hasErrors() || userExist != null){
            if (userExist != null){
            model.addAttribute("userExist", true);
        }
        return "security/register";
    }else {
            this.userService.registerUser(user);
            return "redirect:/login";
        }

    }

}
