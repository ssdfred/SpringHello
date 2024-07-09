package fr.diginamic.dto;

/**
 * 
 */
public class DepartementDto {

	private String code;
	private String name;
	private int population;

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
	 * Getter pour name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter pour name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
}
