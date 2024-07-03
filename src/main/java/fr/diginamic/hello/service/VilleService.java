package fr.diginamic.hello.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.VilleRepository;
import fr.diginamic.hello.entytes.Ville;

@Service
public class VilleService {

	@Autowired
	private VilleRepository villeRepository;

	public List<Ville> extractVilles() {
		return villeRepository.findAll();
	}
	public Ville extractVille(int idville) {
		Optional<Ville> ville = villeRepository.findById(idville);
		return ville.orElse(null);
	}
	
	public Ville extractVille(String nom) {
		Optional<Ville> ville = villeRepository.findBynom(nom);
		return ville.orElse(null);
	}

	public List<Ville> insertVille(Ville ville) {
		villeRepository.save(ville);
		return villeRepository.findAll();
	}
	public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Optional<Ville> villeOpt = villeRepository.findById(idVille);
        if (villeOpt.isPresent()) {
            Ville ville = villeOpt.get();
            ville.setNom(villeModifiee.getNom());
            ville.setNbHabitants(villeModifiee.getNbHabitants());
            villeRepository.save(ville);
        }
        return villeRepository.findAll();
    }

    public List<Ville> supprimerVille(int idVille) {
        villeRepository.deleteById(idVille);
        return villeRepository.findAll();
    }
}
