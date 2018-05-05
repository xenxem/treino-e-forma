package br.com.treinoeforma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.GrupoMuscular;
import br.com.treinoeforma.service.ExercicioImpl;
import br.com.treinoeforma.service.GrupoMuscularImpl;
import br.com.treinoeforma.utils.PageWrapper;

@Controller
@Transactional
public class TreinoController {
	
	@Autowired 
	private ExercicioImpl exercicioImpl;	
	@Autowired 
	private GrupoMuscularImpl grupoMuscularImpl;
	

	@RequestMapping(method = RequestMethod.GET, path = "/montarTreino")
	public ModelAndView montarTreino(Pageable pageable) {		
		 PageWrapper<Exercicio> page = new PageWrapper<>(this.exercicioImpl.buscarPaginando(pageable),"/montarTreino");
		 List<Exercicio> exercicios = page.getContent();
		 List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		 ModelAndView mv = new ModelAndView("treino/form-sel-exerc");
		 mv.addObject("exercicios",exercicios);
		 mv.addObject("grupoMuscular",grupoMuscular);		
		 mv.addObject("page",page);
		 mv.addObject(new Exercicio());
		 return mv;
	}
	
	@RequestMapping("/listarTreino")
	public String listarTreino() {
		
		return "sobre";
	}
	
		
	
}
