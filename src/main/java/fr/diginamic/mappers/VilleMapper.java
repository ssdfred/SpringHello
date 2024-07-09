package fr.diginamic.mappers;

import fr.diginamic.dto.VilleDto;
import fr.diginamic.entities.Ville;

public class VilleMapper {

	public static VilleDto toDto(Ville ville) {
		VilleDto dto = new VilleDto();
		dto.setCode(ville.getCodeVille());
		dto.setPopulationTotale(ville.getPopulationTotale());
		dto.setDepartementCode(ville.getDepartement().getCode());
		dto.setDepartementName(ville.getDepartement().getNom());
		return dto;
	}

	public static Ville toEntity(VilleDto dto) {
		Ville ville = new Ville();
		ville.setCodeVille(dto.getCode());
		ville.setPopulationTotale(dto.getPopulationTotale());
		return ville;
	}
}
