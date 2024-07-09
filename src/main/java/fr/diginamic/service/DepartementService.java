package fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.repository.DepartementRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class DepartementService {
	@Autowired
	private DepartementRepository departementRepository;

	@Transactional
	public Departement ajouterDepartement(Departement departement) {
		if (departementRepository.findByNom(departement.getNom()).isPresent()) {
			throw new IllegalArgumentException("Le département existe déjà");
		}
		return departementRepository.save(departement);
	}

	public List<Departement> getAllDepartements() {
		return departementRepository.findAll();
	}

	public Departement getDepartementById(int id) {
		return departementRepository.findById(id).orElse(null);
	}

	public Departement updateDepartement(int id, Departement departement) {
		Departement departementExistant = departementRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
		departementExistant.setNom(departement.getNom());
		return departementRepository.save(departementExistant);
	}

	public void deleteDepartement(int id) {
		Departement departement = departementRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
		departementRepository.delete(departement);
	}

	public List<Ville> getNPlusGrandesVilles(int id, int n) {
		Pageable pageable = PageRequest.of(0, n);
		return departementRepository.findNPlusGrandesVilles(id, pageable).getContent();
	}

	public List<Ville> getVillesByPopulationRange(int departementId, int min, int max) {
		return departementRepository.findVillesByPopulationTotaleRange(departementId, min, max);
	}

	public Departement getDepartementByNom(String nom) {
		Optional<Departement> optionalDepartement = departementRepository.findByNom(nom);
		if (optionalDepartement.isPresent()) {
			return optionalDepartement.get();
		} else {
			throw new EntityNotFoundException("Département avec le nom '" + nom + "' non trouvé");
		}
	}

	public Departement getDepartementByCode(String code) {
        Iterable<Departement> departements = departementRepository.findByCode(code);
        
        for (Departement departement : departements) {
            // On retourne le premier département trouvé correspondant au code
            return departement;
        }

        // Si aucun département correspondant n'est trouvé, on lance une exception
        throw new EntityNotFoundException("Département avec le code '" + code + "' non trouvé");
    }
}