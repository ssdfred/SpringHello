package fr.diginamic.hello.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.hello.entytes.Departement;
import fr.diginamic.hello.entytes.DepartementDao;
import fr.diginamic.hello.entytes.Ville;
import fr.diginamic.hello.entytes.VilleDao;


@Service
@Transactional
public class VilleService {

	 @Autowired
	    private VilleDao villeDao;

	    @Autowired
	    private DepartementDao departementDao;
	    
	    public Ville ajouterVilleAvecDepartement(Ville ville, String nomDepartement) {
	        Departement departement = departementDao.findByNom(nomDepartement);
	        if (departement == null) {
	            departement = new Departement();
	            departement.setNom(nomDepartement);
	            departementDao.save(departement);
	        }
	        ville.setDepartement(departement);
	        return villeDao.save(ville);
	    }
	    public List<Ville> getAllVilles() {
	        return villeDao.findAll();
	    }

	    public Ville getVilleById(int id) {
	        return villeDao.findById(id);
	    }

	    public Ville createVille(Ville ville) {
	        return villeDao.save(ville);
	    }

	    public Ville updateVille(int id, Ville ville) {
	        return villeDao.update(ville);
	    }

	    public void deleteVille(Ville ville) {
	        villeDao.delete(ville);
	    }
	    public List<Ville> villesParDepartement(int departementId) {
	        return villeDao.findByDepartementId(departementId);
	    }

	
	}