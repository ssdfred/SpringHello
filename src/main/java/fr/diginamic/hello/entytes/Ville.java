package fr.diginamic.hello.entytes;

import org.springframework.stereotype.Component;


public class Ville {
	private int id;
    private String nom;
    private int nbHabitants;
	// Constructeur
    public Ville(int id, String nom, int nbHabitants) {
    	this.id =id;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

	/** Getter pour id
	 * @return the id 
	*/
	public int getId() {
		return id;
	}

	/** Setter pour id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}


}
