package br.com.treinoeforma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinoeforma.model.Usuario;

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
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:index";
	}
	
	@RequestMapping("/registro")
	public ModelAndView registro() {		
		ModelAndView mv = new ModelAndView("registro");
		mv.addObject("usuario", new Usuario());
		return mv;		
	}

}
