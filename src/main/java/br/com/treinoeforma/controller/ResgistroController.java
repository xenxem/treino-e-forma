package br.com.treinoeforma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.treinoeforma.config.SecurityConfig;
import br.com.treinoeforma.model.Usuario;

@Controller
public class ResgistroController {
	
	
	@Autowired
	private SecurityConfig sc;
	
	@RequestMapping("/cadastro")
	public String cadastro(Usuario usuario) {		
		
		System.out.println(usuario.getLogin());
		
		
		return "login";
	}
	
	

}
