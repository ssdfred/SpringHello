package fr.diginamic.controller;

import fr.diginamic.dto.VilleDto;
import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.mappers.VilleMapper;
import fr.diginamic.repository.DepartementRepository;
import fr.diginamic.repository.VilleRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/villes")
public class VilleController {

	private final VilleRepository villeRepository;
	private final DepartementRepository departementRepository;

	public VilleController(VilleRepository villeRepository, DepartementRepository departementRepository) {
		this.villeRepository = villeRepository;
		this.departementRepository = departementRepository;
	}

	@PostMapping
    public ResponseEntity<Object> createVille(@RequestBody VilleDto villeDto) {
        if (villeDto.getDepartement() == null || villeDto.getDepartement().getNom() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le département et son nom sont obligatoires");
        }

        Departement departement = departementRepository.findByNom(villeDto.getDepartement().getNom());

        if (departement == null) {
            departement = new Departement();
            departement.setNom(villeDto.getDepartement().getNom());
            departement.setCode(villeDto.getDepartement().getCode());
            departement = departementRepository.save(departement);
        }

        Ville ville = VilleMapper.toEntity(villeDto);
        ville.setDepartement(departement);
        ville = villeRepository.save(ville);

        return ResponseEntity.status(HttpStatus.CREATED).body(VilleMapper.toDto(ville));
    }

//	@GetMapping("/{id}/villes/filter")
//	public ResponseEntity<List<VilleDto>> getVillesByPopulationRange(@PathVariable int id, @RequestParam int min,
//			@RequestParam int max) {
//		List<Ville> villes = villeRepository.findVillesByPopulationTotaleRange(id, min, max);
//		List<VilleDto> villeDtos = villes.stream().map(VilleMapper::toDto).collect(Collectors.toList());
//		return new ResponseEntity<>(villeDtos, HttpStatus.OK);
//	}
}
