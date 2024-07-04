package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entytes.Departement;
import fr.diginamic.hello.entytes.Ville;
import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ville")
public class VilleController {

	@Autowired
    private VilleService villeService;

    @GetMapping
    public ResponseEntity<List<Ville>> getAllVilles() {
        List<Ville> villes = villeService.getAllVilles();
        return ResponseEntity.ok().body(villes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        Ville ville = villeService.getVilleById(id);
        if (ville == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ville);
    }

    @PostMapping
    public ResponseEntity<Ville> ajouterVille(@RequestBody Ville ville) {
        Ville nouvelleVille = villeService.ajouterVilleAvecDepartement(ville, ville.getDepartement().getNom());
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleVille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ville> updateVille(@PathVariable int id,@RequestBody Ville villeModifiee, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Ville updatedVille = villeService.updateVille(id, villeModifiee);
        
        if (updatedVille == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(updatedVille);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable int id) {
        Ville ville = villeService.getVilleById(id);
        if (ville == null) {
            return ResponseEntity.notFound().build();
        }
        villeService.deleteVille(ville);
        return ResponseEntity.noContent().build();
    }

	// Méthodes spécifiques pour les départements (facultatif)
    @Autowired
    private DepartementService departementService;
    
    @GetMapping("/departements")
    public ResponseEntity<List<Departement>> getAllDepartements() {
    	List<Departement> departements = departementService.getAllDepartements();
    	return ResponseEntity.ok().body(departements);
    }

    @GetMapping("/departements/{id}/villes")
    public List<Ville> getVillesByDepartement(@PathVariable int id) {

        return departementService.villesParDepartement(id);
    }
}
