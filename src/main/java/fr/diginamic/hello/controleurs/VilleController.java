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

	private List<Ville> villes = new ArrayList<>();

	// Constructeur pour initialiser les villes
	public VilleController() {

		villes.add(new Ville(1, "Paris", 2148000));
		villes.add(new Ville(2, "Lyon", 515695));
		villes.add(new Ville(3, "Marseille", 861635));
	}

	@GetMapping
	public List<Ville> getVilles() {
		return villeService.extractVilles();
	}

	@PostMapping
	public ResponseEntity<String> insertVille(@RequestBody Ville nvVille) {
		List<Ville> villes = villeService.insertVille(nvVille);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ville> chercheVille(@PathVariable int id) {
		Optional<Ville> ville = villes.stream().filter(v -> v.getId() == id).findFirst();
		if (ville.isPresent()) {
			return ResponseEntity.ok(ville.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateVille(@PathVariable int id, @RequestBody Ville updatedVille) {
		List<Ville> villes = villeService.modifierVille(id, updatedVille);
		return ResponseEntity.ok("Ville mise à jour avec succès");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVille(@PathVariable int id) {
		List<Ville> villes = villeService.supprimerVille(id);
		return ResponseEntity.ok("Ville supprimée avec succès");
	}
}
