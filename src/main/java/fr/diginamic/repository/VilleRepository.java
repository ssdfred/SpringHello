package fr.diginamic.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {

	Optional<Ville> findByNomAndDepartement(String nom, Departement departement);

	Iterable<Ville> findByDepartementId(Integer departementId);

	Iterable<Ville> findByPopulationTotaleBetween(int min, int max);

	Iterable<Ville> findByPopulationTotaleGreaterThan(int min);

	 List<Ville> findByDepartementAndPopulationTotaleGreaterThan(Departement departement, int minPopulation);
	 List<Ville> findByDepartementId(int departementId);
	 //List<Ville> findTopByDepartementOrderByPopulationTotaleDesc(Departement departement, int id);
}