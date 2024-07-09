package fr.diginamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.repository.DepartementRepository;
import fr.diginamic.repository.VilleRepository;
import fr.diginamic.utils.ParseurVille;
import fr.diginamic.utils.RecensementUtils;

import java.io.File;
import java.util.Optional;

@Service
public class CsvFileReaderService {

	private final VilleRepository villeRepository;
	private final DepartementRepository departementRepository;
	private final RecensementUtils recensementUtils;

	@Autowired
	public CsvFileReaderService(VilleRepository villeRepository, DepartementRepository departementRepository,
			RecensementUtils recensementUtils) {
		this.villeRepository = villeRepository;
		this.departementRepository = departementRepository;
		this.recensementUtils = recensementUtils;
	}

	@Transactional
	public void readAndSaveCsvData(String csvFileName) {
		File csvFile = new File(csvFileName);
		String filePath = csvFile.getAbsolutePath();

		try {
			Departement departement = recensementUtils.lire(filePath);

			if (departement == null) {
				System.out.println("Erreur lors de la lecture du fichier CSV.");
				return;
			}

			Iterable<Departement> existingDepartements = departementRepository.findByCode(departement.getCode());

			if (existingDepartements.iterator().hasNext()) {
				// S'il y a des départements existants pour ce code
				Departement existingDepartement = existingDepartements.iterator().next();
				System.out.println("Département existant trouvé : " + existingDepartement);


				departement = departementRepository.save(existingDepartement);
				System.out.println("Département mis à jour : " + departement);
			} else {
				// S'il n'y a pas de département existant pour ce code
				departement = departementRepository.save(departement);
				System.out.println("Nouveau département enregistré : " + departement);
			}

			for (Ville ville : departement.getVilles()) {
				ville.setDepartement(departement); // Assure que chaque ville a une référence vers le département
				villeRepository.save(ville); // Utilisation correcte du repository pour sauvegarder chaque ville
				System.out.println("Ville enregistrée : " + ville);
			}

			System.out.println("Les données du fichier CSV ont été enregistrées avec succès.");
		} catch (Exception e) {
			// Log the exception
			System.err.println("Erreur lors de l'enregistrement des données du fichier CSV: " + e.getMessage());
			e.printStackTrace();
			throw e; // Re-throw the exception to ensure transaction rollback
		}
	}

}