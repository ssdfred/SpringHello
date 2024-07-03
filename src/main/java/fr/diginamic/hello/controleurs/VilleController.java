package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entytes.Ville;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/ville")
public class VilleController {
    private List<Ville> villes = new ArrayList<>();

    // Constructeur pour initialiser les villes
    public VilleController() {

        villes.add(new Ville(1,"Paris", 2148000));
        villes.add(new Ville(2,"Lyon", 515695));
        villes.add(new Ville(3,"Marseille", 861635));
    }

    @GetMapping
    public List<Ville> getVilles() {
        return villes;
    }
    @PostMapping("/insererVille")
    public ResponseEntity<String> insertVille(@RequestBody Ville nvVille) {
        for (Ville ville : villes) {
            if (ville.getNom().equalsIgnoreCase(nvVille.getNom())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville existe déjà");
            }
        }
        villes.add(nvVille);
        return ResponseEntity.status(HttpStatus.OK).body("Ville insérée avec succès");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ville>  chercheVille(@PathVariable int id) {
    	Optional <Ville> ville = villes.stream().filter(v -> v.getId() == id).findFirst();
    	if (ville.isPresent()) {
    		return ResponseEntity.ok(ville.get());
    	} else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable int id, @RequestBody Ville updateVille) {
    	for (Ville v : villes) {
    		if (v.getId() == id) {
    			v.setNom(updateVille.getNom());
    			v.setNbHabitants(updateVille.getId());
    			return ResponseEntity.ok("Ville mise à jour avec succées");
    		}
    	}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ville non trouvé");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
    	boolean removed = villes.removeIf(ville -> ville.getId() == id);
    	if (removed) {
    		return ResponseEntity.ok("Ville supprimer avec succés");
    	} else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ville non trouvée");
    	}
    }
}
