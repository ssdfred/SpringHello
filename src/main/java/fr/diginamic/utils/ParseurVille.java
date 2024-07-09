package fr.diginamic.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.repository.DepartementRepository;
import fr.diginamic.repository.VilleRepository;

@Component
public class ParseurVille {
	private final DepartementRepository departementRepository;
	private final VilleRepository villeRepository;

	@Autowired
    public ParseurVille(DepartementRepository departementRepository,VilleRepository villeRepository) {
        this.departementRepository = departementRepository;
        this.villeRepository = villeRepository;
        
    }

	/**
	 * Ajoute une ligne représentant une ville
	 * 
	 * @param departement
	 * @param ligne       ligne de laquelle on extrait une ville
	 */
	public void ajoutLigne(Departement departement, String ligne) {
		String[] morceaux = ligne.split(";");
		String codeRegion = morceaux[0];
		String nomRegion = morceaux[1];
		String codeDepartement = morceaux[2];
		String codeCommune = morceaux[5];
		String nomCommune = morceaux[6];
		String population = morceaux[7];
		int populationTotale = Integer.parseInt(population.replace(" ", "").trim());

		// On crée maintenant la ville avec toutes ses données
		Ville ville = new Ville(codeRegion, nomRegion, codeDepartement, codeCommune, nomCommune, populationTotale);

		//
		ville.setCodeRegion(codeRegion);
		ville.setNomRegion(nomRegion);
		ville.setCodeDepartement(codeDepartement);
		ville.setCodeVille(codeCommune);
		ville.setNom(nomCommune);
		ville.setPopulationTotale(populationTotale);

		// On ajoute les departements
		Departement departement1 = new Departement();
		departement1.setNom(nomRegion);
		departement1.setCode(codeDepartement);
		departement1.setPopulation(populationTotale);
		departement1.addVille(ville);

	
	    // Enregistrement de la ville en base de données
	    try {
	        villeRepository.save(ville);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		try {
			departementRepository.save(departement1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
