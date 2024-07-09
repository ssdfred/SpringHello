package fr.diginamic.dto;

/**
 * 
 */
public class VilleDto {
    private String nom;
    private int populationTotale;
    private DepartementDto departement;

    // getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPopulationTotale() {
        return populationTotale;
    }

    public void setPopulationTotale(int populationTotale) {
        this.populationTotale = populationTotale;
    }

    public DepartementDto getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementDto departement) {
        this.departement = departement;
    }
}