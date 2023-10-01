package com.humanbooster.Newbiz;

import com.humanbooster.Newbiz.models.Annonce;
import com.humanbooster.Newbiz.models.Category;
import com.humanbooster.Newbiz.models.Role;
import com.humanbooster.Newbiz.models.User;
import com.humanbooster.Newbiz.services.AnnonceService;
import com.humanbooster.Newbiz.services.CategoryService;
import com.humanbooster.Newbiz.services.RoleService;
import com.humanbooster.Newbiz.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringData {

    private Logger logger = LoggerFactory.getLogger(SpringData.class);

    public static void main(String[] args){
        SpringApplication.run(SpringData.class);
    }

    @Bean
    public CommandLineRunner dataLoader(CategoryService categoryService, RoleService roleService, UserService userService){
        return args -> {

            if (categoryService.findByName("Sport").isEmpty()){
                logger.info("Creation de la categorie");
                Category sport = new Category("Sport", "Sport");
                categoryService.save(sport);
            }

            if (categoryService.findByName("Faits divers").isEmpty()){
                logger.info("Creation de la categorie");
                Category FaitsDivers = new Category("Faits Divers", "Faits Divers");
                categoryService.save(FaitsDivers);
            }

            if (categoryService.findByName("Politique").isEmpty()){
                logger.info("Creation de la categorie");
                Category politique = new Category("Politique", "Politique");
                categoryService.save(politique);
            }

            if (categoryService.findByName("Evenements").isEmpty()){
                logger.info("Creation de la categorie");
                Category evenements = new Category("Evenements", "Evenements");
                categoryService.save(evenements);
            }

            Role roleJournaliste =   roleService.findByName("Journaliste");


            if(roleJournaliste == null){
                roleJournaliste = new Role("Journaliste");
                roleService.saveRole(roleJournaliste);
            }

            if(userService.findByUsername("Journaliste") == null){
                User user = new User();
                user.setUsername("Journaliste");
                user.setPassword("Journaliste");
                user.setFirstname("Mylene");
                user.setLastname("SAID OUSSENI");

                user.addRole(roleJournaliste);

                userService.registerUser(user);
            }


        };




    }

}
