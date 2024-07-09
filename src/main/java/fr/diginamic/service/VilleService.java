package fr.diginamic.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.DepartementDao;
import fr.diginamic.entities.Ville;
import fr.diginamic.entities.VilleDao;
import fr.diginamic.repository.DepartementRepository;
import fr.diginamic.repository.VilleRepository;
import jakarta.persistence.OrderBy;


@Service
@Transactional
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Transactional
//    public Ville ajouterVilleAvecDepartement(Ville ville, String nomDepartement) {
//        Departement departement = departementRepository.findByNom(nomDepartement).orElseGet(() -> {
//            Departement nouveauDepartement = new Departement();
//            nouveauDepartement.setNom(nomDepartement);
//            return departementRepository.save(nouveauDepartement);
//        });
//
//        if (villeRepository.findByNomAndDepartement(ville.getNom(), departement).isPresent()) {
//            throw new IllegalArgumentException("La ville existe déjà dans ce département");
//        }
//
//        ville.setDepartement(departement);
//        return villeRepository.save(ville);
//    }

    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    public Ville getVilleById(int id) {
        return villeRepository.findById(id).orElse(null);
    }

    public Ville createVille(Ville ville) {
        return villeRepository.save(ville);
    }

    public Ville updateVille(int id, Ville ville) {
        Ville villeExistante = villeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ville non trouvée"));
        villeExistante.setNom(ville.getNom());
        villeExistante.setDepartement(ville.getDepartement());
        return villeRepository.save(villeExistante);
    }

    public void deleteVille(int id) {
        Ville ville = villeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ville non trouvée"));
        villeRepository.delete(ville);
    }

//    public Iterable<Ville> villesParDepartement(int departementId) {
//        return villeRepository.findByDepartementId(departementId);
//    }
//
//    public Optional<Ville> findVilleByNomAndDepartement(String nom, Departement departement) {
//        return villeRepository.findByNomAndDepartement(nom, departement);
//    }
//
//    public Iterable<Ville> findVillesByDepartementId(Integer departementId) {
//        return villeRepository.findByDepartementId(departementId);
//    }
//
//    public Iterable<Ville> findVillesByPopulationRange(int min, int max) {
//        return villeRepository.findByPopulationTotaleBetween(min, max);
//    }
//
//    public Iterable<Ville> findVillesByPopulationGreaterThan(int min) {
//        return villeRepository.findByPopulationTotaleGreaterThan(min);
//    }

//    public List<Ville> findTopNVillesByDepartementId(int departementId, int n) {
//        Pageable pageable = (Pageable) PageRequest.of(departementId, 10);
//        return villeRepository.findTopNVillesByDepartementId(departementId, pageable);
//    }
//
//    public List<Ville> findVillesByDepartementAndNbHabitantsBetween(int id, int min, int max) {
//        return villeRepository.findByDepartementIdAndNbHabitantsBetween(id, min, max);
//    }
}