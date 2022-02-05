package monprojet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import monprojet.dao.*;
import monprojet.entity.*;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/projet/ville")

public class CityController {

    @Autowired
    private CityRepository dao;
    @Autowired
    private CountryRepository daoCountry;

    /**
     * Affiche toutes les villes dans la base
     * 
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('showCity.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesVilles(Model model) {
        model.addAttribute("citys", dao.findAll());
        return "showCity";
    }

    /**
     * Montre le formulaire permettant d'ajouter une ville
     * 
     * @param ville initialisé par Spring, valeurs par défaut à afficher dans le
     *              formulaire
     * @return le nom de la vue à afficher ('formulaireVille.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("ville") City city, Model model) {
        model.addAttribute("countrys", daoCountry.findAll());
        return "formulaireVille";
    }

    @PostMapping(path = "save")
    public String ajouteLaVillePuisMontreLaVille(City city) {
        // cf. https://www.baeldung.com/spring-data-crud-repository-save
        dao.save(city); // Ici on peut avoir une erreur (doublon dans un libellé par exemple)
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste
    }

}
