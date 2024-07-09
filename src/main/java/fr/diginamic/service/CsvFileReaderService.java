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

            Optional<Departement> existingDepartementOpt = departementRepository.findByCode(departement.getCode());

            if (existingDepartementOpt.isPresent()) {
                // Si le département existe déjà en base, le récupérer
                Departement existingDepartement = existingDepartementOpt.get();
                System.out.println("Département existant trouvé : " + existingDepartement);

                // Fusionner les villes du département existant avec les nouvelles
                for (Ville ville : departement.getVilles()) {
                    if (!existingDepartement.getVilles().contains(ville)) {
                        existingDepartement.addVille(ville);
                        ville.setDepartement(existingDepartement); // Assurer que chaque ville référence le bon département
                    }
                }

                // Mettre à jour le département existant en base
                departement = departementRepository.save(existingDepartement);
                System.out.println("Département mis à jour : " + departement);
            } else {
                // Si le département n'existe pas en base, l'enregistrer
                departement = departementRepository.save(departement);
                System.out.println("Nouveau département enregistré : " + departement);
            }

            // Enregistrer chaque ville du département
            for (Ville ville : departement.getVilles()) {
                ville.setDepartement(departement); // Assure que chaque ville référence le bon département
                villeRepository.save(ville); // Enregistrer la ville
                System.out.println("Ville enregistrée : " + ville);
            }

            System.out.println("Les données du fichier CSV ont été enregistrées avec succès.");
        } catch (Exception e) {
            // Logguer l'exception
            System.err.println("Erreur lors de l'enregistrement des données du fichier CSV : " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-lancer l'exception pour assurer le rollback de la transaction
        }
    }
}
