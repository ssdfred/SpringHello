package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entytes.Departement;
import fr.diginamic.hello.entytes.DepartementDao;
import fr.diginamic.hello.entytes.Ville;
import fr.diginamic.hello.entytes.VilleDao;

@Service
public class DepartementService {
	@Autowired
	private DepartementDao departementDao;
	@Autowired
	private VilleDao villeDao;

	public List<Departement> getAllDepartements() {
		return departementDao.findAll();
	}

	public Departement getDepartementById(int id) {
		return departementDao.findById(id);
	}

	public Departement createDepartement(Departement departement) {
		return departementDao.save(departement);
	}

	public Departement updateDepartement(Departement departement) {
		return departementDao.update(departement);
	}

	public void deleteDepartement(Departement departement) {
		departementDao.delete(departement);
	}

	public List<Ville> villesParDepartement(int idDepartement) {
		return villeDao.findByDepartementId(idDepartement);
	}
	public List<Ville> findTopVilles(int id, int n) {
		return departementDao.findTopNVilles(id, n);
	}
	public List<Ville> findVillesByPopulationRange(int id, int min, int max) {
		return departementDao.findVillesByPopulationRange(id, min, max);
	}
}