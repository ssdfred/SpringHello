package fr.diginamic.hello.controleurs;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entytes.Departement;
import fr.diginamic.hello.entytes.Ville;
import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;
import jakarta.validation.Valid;

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
    public ResponseEntity<String> ajouterVille(@Valid @RequestBody Ville ville, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Les données passées en paramètre sont incorrectes");
        }

        Ville nouvelleVille = villeService.ajouterVilleAvecDepartement(ville, ville.getDepartement().getNom());
        if (nouvelleVille == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de la ville");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Ville insérée avec succès");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable int id,@RequestBody Ville villeModifiee, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Ville updatedVille = villeService.updateVille(id, villeModifiee);
        
        if (updatedVille == null) {
            return ResponseEntity.badRequest().body("Les données passées en paramètre sont incorrectes");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Villes modifiée avec succès");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        Ville ville = villeService.getVilleById(id);
        if (ville == null) {
            return ResponseEntity.badRequest().body("Les données passées en paramètre sont incorrectes");
        }
        villeService.deleteVille(ville);
        return ResponseEntity.status(HttpStatus.CREATED).body("Villes supprimer avec succès");
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
