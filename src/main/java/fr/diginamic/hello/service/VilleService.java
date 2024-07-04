package fr.diginamic.hello.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import fr.diginamic.hello.VilleRepository;
import fr.diginamic.hello.entytes.Ville;
@Service
public class VilleService {

    private List<Ville> villes = new ArrayList<>();

    public List<Ville> extractVilles() {
        return villes;
    }

    public Ville extractVille(int id) {
        Optional<Ville> ville = villes.stream().filter(v -> v.getId() == id).findFirst();
        return ville.orElse(null);
    }

    public List<Ville> insertVille(Ville ville) {
        Optional<Ville> existingVille = villes.stream().filter(v -> v.getNom().equals(ville.getNom())).findFirst();
        if (existingVille.isPresent()) {
            throw new RuntimeException("La ville existe déjà");
        }
        villes.add(ville);
        return villes;
    }

    public List<Ville> insertVilles(List<Ville> nvVilles) {
        for (Ville ville : nvVilles) {
            Optional<Ville> existingVille = villes.stream().filter(v -> v.getNom().equals(ville.getNom())).findFirst();
            if (existingVille.isPresent()) {
                throw new RuntimeException("La ville " + ville.getNom() + " existe déjà");
            }
        }
        villes.addAll(nvVilles);
        return villes;
    }

    public List<Ville> modifierVille(int id, Ville updatedVille) {
        Optional<Ville> existingVille = villes.stream().filter(v -> v.getId() == id).findFirst();
        if (existingVille.isPresent()) {
            Ville ville = existingVille.get();
            ville.setNom(updatedVille.getNom());
            ville.setNbHabitants(updatedVille.getNbHabitants());
            return villes;
        } else {
            return null;
        }
    }

    public List<Ville> supprimerVille(int id) {
        Optional<Ville> existingVille = villes.stream().filter(v -> v.getId() == id).findFirst();
        if (existingVille.isPresent()) {
            villes.remove(existingVille.get());
            return villes;
        } else {
            return null;
        }
    }
}
