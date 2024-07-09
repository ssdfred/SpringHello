package fr.diginamic.mappers;

import fr.diginamic.dto.VilleDto;
import fr.diginamic.entities.Ville;

public class VilleMapper {
    public static VilleDto toDto(Ville ville) {
        VilleDto villeDto = new VilleDto();
        villeDto.setNom(ville.getNom());
        villeDto.setPopulationTotale(ville.getPopulationTotale());
        villeDto.setDepartement(DepartementMapper.toDto(ville.getDepartement()));
        return villeDto;
    }

    public static Ville toEntity(VilleDto villeDto) {
        Ville ville = new Ville();
        ville.setNom(villeDto.getNom());
        ville.setPopulationTotale(villeDto.getPopulationTotale());
        return ville;
    }
}
