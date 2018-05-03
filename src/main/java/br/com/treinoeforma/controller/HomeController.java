package br.com.treinoeforma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/sobre")
	public String sobre() {
		return "sobre";
	}
	
	@RequestMapping("/contato")
	public String contato() {
		return "redirect:contato";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:index";
	}
	
	@RequestMapping("/registro")
	public String registro() {
		return "registro";
	}

}
