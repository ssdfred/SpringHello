package fr.diginamic.hello.entytes;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany
    private List<Ville> villes;

    public Departement() {
    	
    }
	/** Constructeur
	 * @param id
	 * @param nom
	 * 
	 */
	public Departement(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
		
	}
	/** Getter pour id
	 * @return the id 
	*/
	public Long getId() {
		return id;
	}
	/** Setter pour id
	 * @param id
	 */
	public void setId(Long id) {
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
