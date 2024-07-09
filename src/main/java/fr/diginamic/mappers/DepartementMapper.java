package fr.diginamic.mappers;

import fr.diginamic.dto.DepartementDto;
import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;

public class DepartementMapper {
	public static DepartementDto toDto(Departement departement) {
		DepartementDto dto = new DepartementDto();
		dto.setCode(departement.getCode());
		dto.setName(departement.getNom());
		dto.setPopulation(departement.getVilles().stream().mapToInt(Ville::getPopulationTotale).sum());
		return dto;
	}

	public static Departement toEntity(DepartementDto dto) {
		Departement departement = new Departement();
		departement.setCode(dto.getCode());
		departement.setNom(dto.getName());
		return departement;
	}
}
