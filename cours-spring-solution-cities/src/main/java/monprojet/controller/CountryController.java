package monprojet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monprojet.dao.*;
import monprojet.dto.*;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/projet/pays")

public class CountryController {

    @Autowired
    private CountryRepository dao;

    /**
     * Affiche tous les pays dans la base
     * 
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('showCountry.html')
     */
    @GetMapping(path = "show")
    public String afficheTousLesPays(Model model) {
        model.addAttribute("countrys", dao.findAll());
        return "showCountry";
    }

}
