package fr.diginamic.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.entities.Departement;

@Component
public class RecensementUtils {
	 private final ParseurVille parseurVille;

	    @Autowired
	    public RecensementUtils(ParseurVille parseurVille) {
	        this.parseurVille = parseurVille;
	    }

	    @Transactional
	    public Departement lire(String cheminFichier) {
	        Departement departement = new Departement();

	        List<String> lignes = null;
	        try {
	            File file = new File(cheminFichier);
	            lignes = FileUtils.readLines(file, "UTF-8");

	            // Supprimer la ligne d'en-tête avec les noms des colonnes
	            if (!lignes.isEmpty()) {
	                lignes.remove(0);
	            }

	            for (String ligne : lignes) {
	                parseurVille.ajoutLigne(departement, ligne);
	            }
	            return departement;

	        } catch (IOException e) {
	            System.out.println("Erreur lors de la lecture du fichier: " + e.getMessage());
	            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
	        }
	    }
	}