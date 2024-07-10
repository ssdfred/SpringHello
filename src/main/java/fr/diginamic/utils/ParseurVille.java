package fr.diginamic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.diginamic.dto.DepartementDto;
import fr.diginamic.dto.VilleDto;
import fr.diginamic.entities.Departement;
import fr.diginamic.entities.Ville;
import fr.diginamic.mappers.VilleMapper;
import fr.diginamic.repository.VilleRepository;

@Component
public class ParseurVille {

    private final VilleRepository villeRepository;

    @Autowired
    public ParseurVille(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    /**
     * Adds a Ville entity to the specified Departement.
     *
     * @param departement The departement to which the Ville should be added.
     * @param villeDto    The Ville DTO containing the data to be added.
     */
    public void ajoutVille(Departement departement, VilleDto villeDto) {
        // Validate city name length
        if (villeDto.getNom().length() < 2 || villeDto.getNom().length() > 100) {
            System.out.println("Invalid city name length: " + villeDto.getNom());
            return;
        }

        Ville ville = VilleMapper.toEntity(villeDto);
        ville.setDepartement(departement);
        villeRepository.save(ville);
    }

    /**
     * Adds a line representing a city to the specified DepartementDto.
     *
     * @param departementDto The DepartementDto to which the city should be added.
     * @param ligne          The line from which we extract the city data.
     */
    public void ajoutLigne(DepartementDto departementDto, String ligne) {
        String[] morceaux = ligne.split(";");
        String codeRegion = morceaux[0];
        String nomRegion = morceaux[1];
        String codeDepartement = morceaux[2];
        String codeCommune = morceaux[5];
        String nomCommune = morceaux[6];
        String population = morceaux[7];
        int populationTotale = Integer.parseInt(population.replace(" ", "").trim());

        // Now create the VilleDto with all its data
        VilleDto villeDto = new VilleDto();
        villeDto.setCodeRegion(codeRegion);
        villeDto.setNomRegion(nomRegion);
        villeDto.setCodeDepartement(codeDepartement);
        villeDto.setCodeVille(codeCommune);
        villeDto.setNom(nomCommune);
        villeDto.setPopulationTotale(populationTotale);
        villeDto.setDepartement(departementDto);

        // Add the VilleDto to the DepartementDto
        departementDto.addVille(villeDto);
    }
}
