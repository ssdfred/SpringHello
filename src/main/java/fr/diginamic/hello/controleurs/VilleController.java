package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entytes.Ville;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/ville")
public class VilleController {

	@GetMapping
	public List<Ville> getVilles() {
		List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 2148000));
        villes.add(new Ville("Lyon", 515695));
        villes.add(new Ville("Marseille", 861635));
        return villes;
	}
	
	

}
