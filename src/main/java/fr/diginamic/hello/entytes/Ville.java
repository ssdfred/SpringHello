package fr.diginamic.hello.entytes;



//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



//@Entity
public class Ville {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min (1)
	private int id;
	@Size(min=2, max=100)
	@NotNull
    private String nom;
	
	@Min(1)
    private int nbHabitants;
	// Constructeur
    public Ville() {
    	
    }
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
