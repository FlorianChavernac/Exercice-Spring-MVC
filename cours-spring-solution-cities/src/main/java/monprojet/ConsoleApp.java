package monprojet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import monprojet.dao.*;
import monprojet.entity.*;

@Component
@Log4j2 // Génère le 'logger' pour afficher les messages de trace
public class ConsoleApp implements CommandLineRunner {
    @Autowired // Auto-initialisé par Spring
    private CountryRepository countryDAO;
 
    @Override
    /*
     * Equivalent de la méthode 'main' pour une application Spring Boot
     **/
    public void run(String... args) throws Exception {
              log.info("On liste tous les enregistrements de la table 'Country'");
            List<Country> tousLesPays = countryDAO.findAll();
            for (Country c : tousLesPays) {
                System.out.println(c);
            }
            
            tapezEnterPourContinuer();
    
            log.info("On ajoute un nouvel enregistrement");
            Country espagne = new Country("ES", "España");
            log.info("Avant d'enregistrer, pas de clé : {}", espagne);
            countryDAO.save(espagne);
            log.info("Après l'enregistrement, la clé a été générée : {}", espagne);
    
            tapezEnterPourContinuer();
    
            log.info("Recherche par clé");
            Optional<Country> oc = countryDAO.findById(2);
            oc.ifPresent(c -> log.info("On a trouvé: {}", c));
    
            tapezEnterPourContinuer();
    
            log.info("Suppression par clé");
            log.info("Avant la suppression il y a {} enregistrements", countryDAO.count());
            try { 
                countryDAO.deleteById(2); // Impossible de la détruire, car elle est référencée par des villes 
            } catch (DataIntegrityViolationException e) { 
                log.error("Impossible de supprimer : {}", e.getMessage()); 
                // cf. https://www.baeldung.com/spring-dataIntegrityviolationexception
            }
            log.info("Il reste toujours {} enregistrements", countryDAO.count());           
   }

    public static void tapezEnterPourContinuer() throws Exception {
        System.out.println("Tapez \"ENTER\" pour continuer...");
        System.in.read();
    }
}
