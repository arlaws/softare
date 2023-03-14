package bj.edem.softcare.apis;

import bj.edem.softcare.entities.Personne;
import bj.edem.softcare.services.LogsService;
import bj.edem.softcare.services.PersonneService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import bj.edem.softcare.common.AppRequestException;

@RestController
@RequestMapping(value = "/personne")
public class PersonneApiController {

    @Autowired
    LogsService logService;

    @Autowired
    PersonneService personneService;

    //Toute la liste
    @RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Personne> all(Model model, HttpServletRequest request) {
        //Users initiateur = userService.findByUsername(request.getUserPrincipal().getName());
        return personneService.findAll();
    }

    //Ajouter
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Personne> add(@RequestBody Personne personne, HttpServletRequest request) {
        try {
            //logService.save("Ajout de la personne " + personne.getNomPrenoms() + ".", request);
            return new ResponseEntity<>(personneService.save(personne), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Retrouver une occurence
    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Personne> req(@PathVariable Long id) {
        return personneService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Personne> edit(@PathVariable("id") Long id, @RequestBody Personne personne,
            HttpServletRequest request) throws Throwable {
        try {
            Personne selectedPersonne = personneService.findById(id).get();
            
            selectedPersonne.setCivilite(personne.getCivilite());
            selectedPersonne.setNom(personne.getNom());
            selectedPersonne.setPrenoms(personne.getPrenoms());
            selectedPersonne.setGenre(personne.getGenre());
            selectedPersonne.setGenre(personne.getGenre());
            selectedPersonne.setSituationMatrimoniale(personne.getSituationMatrimoniale());
            selectedPersonne.setDateNaissance(personne.getDateNaissance());
            selectedPersonne.setContact(personne.getContact());
            selectedPersonne.setEmail(personne.getEmail());
            selectedPersonne.setNomPrenoms(personne.getNomPrenoms());
            logService.save("Mise à jour de la personne : " + personne.getId() + " - "+personne.getNomPrenoms()+". ", request);
            return new ResponseEntity<>(personneService.save(personne), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<Personne> del(@PathVariable("id") Long id) {
        return new ResponseEntity<>(personneService.delete(id)
                .orElseThrow(() -> new AppRequestException("Personne", id)), HttpStatus.NO_CONTENT);
    }

}
