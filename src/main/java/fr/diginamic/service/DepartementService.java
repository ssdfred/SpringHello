package fr.diginamic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.dto.DepartementDto;
import fr.diginamic.entities.Departement;

@Service
@Transactional
public class DepartementService {
    public DepartementDto convertToDto(Departement departement) {
        DepartementDto departementDto = new DepartementDto();
        departementDto.setCode(departement.getCode());
        departementDto.setNom(departement.getNom());

        return departementDto;
    }
}