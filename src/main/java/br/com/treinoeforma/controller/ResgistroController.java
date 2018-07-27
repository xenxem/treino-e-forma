package br.com.treinoeforma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.treinoeforma.model.Usuario;

@Controller
public class ResgistroController {
	
	@RequestMapping("/cadastro")
	public String cadastro(Usuario usuario) {	
		
		
		
		return "login";
	}
	
	

}
