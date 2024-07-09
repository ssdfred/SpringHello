package fr.diginamic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {

	Optional<Departement> findByNom(String nom);

    Iterable<Departement> findByCode(String code);

    Optional<Departement> findById(int id);

    @Query("SELECT v FROM Ville v WHERE v.departement.id = :departementId AND v.populationTotale BETWEEN :min AND :max")
    List<Ville> findVillesByPopulationTotaleRange(@Param("departementId") int departementId, @Param("min") int min,
            @Param("max") int max);

    @Query("SELECT v FROM Ville v WHERE v.departement.id = :departementId ORDER BY v.populationTotale DESC")
    Page<Ville> findNPlusGrandesVilles(@Param("departementId") int departementId, Pageable pageable);


}
