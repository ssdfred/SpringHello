package fr.diginamic.hello.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class HelloService {

	
	public String salutation() {
		return "Je suis la classe de service et je vous dis Bonjour";
	}

}
