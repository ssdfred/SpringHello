package fr.diginamic.dto;

/**
 * 
 */
public class VilleDto {

	private String code;
	private int populationTotale;
	private String departementCode;
	private String departementName;

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

	/**
	 * Getter pour populationTotale
	 * 
	 * @return the population
	 */
	public int getPopulationTotale() {
		return populationTotale;
	}

	/**
	 * Setter pour population
	 * 
	 * @param populationTotale
	 */
	public void setPopulationTotale(int population) {
		this.populationTotale = population;
	}

	/**
	 * Getter pour departementCode
	 * 
	 * @return the departementCode
	 */
	public String getDepartementCode() {
		return departementCode;
	}

	/**
	 * Setter pour departementCode
	 * 
	 * @param departementCode
	 */
	public void setDepartementCode(String departementCode) {
		this.departementCode = departementCode;
	}

	/**
	 * Getter pour departementName
	 * 
	 * @return the departementName
	 */
	public String getDepartementName() {
		return departementName;
	}

	/**
	 * Setter pour departementName
	 * 
	 * @param departementName
	 */
	public void setDepartementName(String departementName) {
		this.departementName = departementName;
	}
}
