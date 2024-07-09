package fr.diginamic.controller;

import fr.diginamic.dto.DepartementDto;
import fr.diginamic.dto.VilleDto;
import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.mappers.DepartementMapper;
import fr.diginamic.mappers.VilleMapper;
import fr.diginamic.repository.DepartementRepository;
import fr.diginamic.repository.VilleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<DepartementDto> getDepartementById(@PathVariable int id) {
        Optional<Departement> departementOpt = departementRepository.findById(id);
        if (departementOpt.isPresent()) {
            DepartementDto departementDto = DepartementMapper.toDto(departementOpt.get());
            return new ResponseEntity<>(departementDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public DepartementDto createDepartement(@RequestBody DepartementDto departementDto) {
        Departement departement = DepartementMapper.toEntity(departementDto);
        Departement savedDepartement = departementRepository.save(departement);
        return DepartementMapper.toDto(savedDepartement);
    }

    @PutMapping("/{id}")
    public DepartementDto updateDepartement(@PathVariable int id, @RequestBody DepartementDto departementDto) {
        Departement departement = departementRepository.findById(id).orElseThrow();
        departement.setCode(departementDto.getCode());
        departement.setNom(departementDto.getNom());
        
        Departement updatedDepartement = departementRepository.save(departement);
        return DepartementMapper.toDto(updatedDepartement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartement(@PathVariable int id) {
        Departement departement = departementRepository.findById(id).orElseThrow();
        departementRepository.delete(departement);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/villes")
    public ResponseEntity<List<VilleDto>> getVillesByPopulationRange(
            @PathVariable int id,
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max) {

        List<Ville> villes;

        if (min != null && max != null) {
            villes = departementRepository.findVillesByPopulationTotaleRange(id, min, max);
        } else {
            villes = departementRepository.findTopNVillesByDepartementOrderByPopulationTotaleDesc(id);
        }

        List<VilleDto> villeDtos = villes.stream().map(VilleMapper::toDto).collect(Collectors.toList());

        // Ajoutez des logs pour le débogage
        System.out.println("Paramètres min et max : min = " + min + ", max = " + max);
        System.out.println("Résultat de la requête SQL : " + villeDtos);

        return  ResponseEntity.ok().body(villeDtos);
    }
}
