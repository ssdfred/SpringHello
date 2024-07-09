package fr.diginamic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entities.Ville;
import fr.diginamic.repository.VilleRepository;
import fr.diginamic.service.VilleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ville")
public class VilleController {
	@Autowired
	private VilleService villeService;

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

	@GetMapping
	public ResponseEntity<List<Ville>> getAllVilles() {
		List<Ville> villes = villeService.getAllVilles();
		return ResponseEntity.ok(villes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
		Ville ville = villeService.getVilleById(id);
		if (ville != null) {
			return ResponseEntity.ok(ville);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ville> updateVille(@PathVariable int id, @RequestBody Ville ville) {
		try {
			Ville updatedVille = villeService.updateVille(id, ville);
			return ResponseEntity.ok(updatedVille);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVille(@PathVariable int id) {
		try {
			villeService.deleteVille(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/population/{min}")
	public ResponseEntity<Iterable<Ville>> getVillesByPopulationGreaterThan(@PathVariable("min") int min) {
		Iterable<Ville> villes = villeService.findVillesByPopulationGreaterThan(min);
		return ResponseEntity.ok(villes);
	}

}
