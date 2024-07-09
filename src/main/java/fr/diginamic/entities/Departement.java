package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.sql.ast.tree.from.MappedByTableGroup;

import com.opencsv.bean.FieldMapByName;

@Entity
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;
	/** code : String */
	private String code;
	/** population : int */
	private int population;
	
	@OneToMany(mappedBy ="departement", cascade = CascadeType.ALL)
	
	private List<Ville> villes = new ArrayList<>();

	public Departement() {

	}

	/**
	 * Constructeur
	 * 
	 * @param code
	 * 
	 */
	public Departement(String code) {
		super();

		this.code = code;

	}


	/**
	 * Ajoute une ville
	 * 
	 * @param ville ville
	 */
    public void addVille(Ville ville) {
        ((List<Ville>) villes).add(ville);
        ville.setDepartement(this);
    }

    public void removeVille(Ville ville) {
        ((List<Ville>) villes).remove(ville);
        ville.setDepartement(null);
    }


	/**
	 * Getter pour id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter pour id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter pour nom
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter pour nom
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

 

	/**
	 * Getter pour population
	 * 
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * Setter pour population
	 * 
	 * @param population
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * Getter pour code
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter pour code
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/** Getter pour villes
	 * @return the villes 
	*/
	public List<Ville> getVilles() {
		return villes;
	}

	/** Setter pour villes
	 * @param villes
	 */
	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}





}
