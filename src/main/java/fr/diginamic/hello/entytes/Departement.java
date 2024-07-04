package fr.diginamic.hello.entytes;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;


    public Departement() {
    	
    }
	/** Constructeur
	 * @param id
	 * @param nom
	 * @param villes
	 */
	public Departement(int id, String nom ) {
		super();
		this.id = id;
		this.nom = nom;
		
		
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
	/** Getter pour nom
	 * @return the nom 
	*/
	public String getNom() {
		return nom;
	}
	/** Setter pour nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


}
