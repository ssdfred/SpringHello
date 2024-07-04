package fr.diginamic.hello;


import java.util.Optional;

//import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.hello.entytes.Ville;

public interface VilleRepository /*extends JpaRepository<Ville, Integer>*/ {

	Optional<Ville> findBynom(String nom);
}
