package fr.diginamic.mappers;

import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.dto.DepartementDto;
import fr.diginamic.entities.Departement;

@Transactional
public class DepartementMapper {
    public static DepartementDto toDto(Departement departement) {
        DepartementDto dto = new DepartementDto();
        dto.setCode(departement.getCode());
        dto.setNom(departement.getNom());
        return dto;
    }

    public static Departement toEntity(DepartementDto dto) {
        Departement departement = new Departement();
        departement.setCode(dto.getCode());
        departement.setNom(dto.getNom());
        return departement;
    }
}