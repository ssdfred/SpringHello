package fr.diginamic.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.dto.DepartementDto;
import fr.diginamic.dto.VilleDto;
import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.mappers.DepartementMapper;
import fr.diginamic.mappers.VilleMapper;
import fr.diginamic.repository.DepartementRepository;
import fr.diginamic.repository.VilleRepository;
import fr.diginamic.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementController {

	private final DepartementRepository departementRepository;
    private final VilleRepository villeRepository;

    public DepartementController(DepartementRepository departementRepository, VilleRepository villeRepository) {
        this.departementRepository = departementRepository;
        this.villeRepository = villeRepository;
    }

    @GetMapping
    public List<DepartementDto> getAllDepartements() {
        List<Departement> departements = departementRepository.findAll();
        return departements.stream()
                .map(DepartementMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DepartementDto getDepartementById(@PathVariable Long id) {
        Departement departement = departementRepository.findById(id).orElseThrow();
        return DepartementMapper.toDto(departement);
    }

    @PostMapping
    public DepartementDto createDepartement(@RequestBody DepartementDto departementDto) {
        Departement departement = DepartementMapper.toEntity(departementDto);
        Departement savedDepartement = departementRepository.save(departement);
        return DepartementMapper.toDto(savedDepartement);
    }

    @PutMapping("/{id}")
    public DepartementDto updateDepartement(@PathVariable Long id, @RequestBody DepartementDto departementDto) {
        Departement departement = departementRepository.findById(id).orElseThrow();
        departement.setCode(departementDto.getCode());
        departement.setName(departementDto.getName());
        Departement updatedDepartement = departementRepository.save(departement);
        return DepartementMapper.toDto(updatedDepartement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartement(@PathVariable Long id) {
        Departement departement = departementRepository.findById(id).orElseThrow();
        departementRepository.delete(departement);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/villes")
    public List<VilleDto> getTopNVilles(@PathVariable Long id, @RequestParam int n) {
        List<Ville> villes = villeRepository.findTopNVillesByDepartementId(id, n);
        return villes.stream().map(VilleMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}/villes/filter")
    public List<VilleDto> getVillesByPopulationRange(@PathVariable Long id, @RequestParam int min, @RequestParam int max) {
        List<Ville> villes = villeRepository.findVillesByPopulationRange(id, min, max);
        return villes.stream().map(VilleMapper::toDto).collect(Collectors.toList());
    }
}
