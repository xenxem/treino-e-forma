package br.com.treinoeforma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.GrupoMuscular;
import br.com.treinoeforma.service.ExercicioImpl;
import br.com.treinoeforma.service.GrupoMuscularImpl;

@Controller
@Transactional
public class TreinoExercicioController {
	
	
	@Autowired 
	private ExercicioImpl exercicioImpl;	
	@Autowired 
	private GrupoMuscularImpl grupoMuscularImpl;
	
	
	@RequestMapping(method = RequestMethod.POST, path="/montar",params="action=novo")
	public ModelAndView novo(HttpServletRequest request) {
				
		String codigoExercicio = request.getParameter("codigoExercicio");
		
		//List<String> lista = Arrays.asList(hidExercicio.split(","));
		
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);
		mv.addObject("codigosMarcados",codigoExercicio);
		mv.addObject(new Exercicio());
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path="/montar",params="action=alterar")
	public ModelAndView alterar(HttpServletRequest request) {
				
		String codigoExercicio = request.getParameter("codigoExercicio");
		
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);
		mv.addObject("codigosMarcados",codigoExercicio);
		mv.addObject(new Exercicio());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/montar",params="action=excluir")
	public ModelAndView excluir() {
		
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);		
		mv.addObject(new Exercicio());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path="/montar",params="action=pdf")
	public ModelAndView pdf() {
		
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);		
		mv.addObject(new Exercicio());
		return mv;
	}


}
