package br.com.treinoeforma.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.service.TituloImpl;
import br.com.treinoeforma.service.TreinoExercicioImpl;
import br.com.treinoeforma.service.TreinoImpl;

@Controller
@Transactional
public class TreinoController {
	
	
	@Autowired 
	private TreinoExercicioImpl treinoExercicioImpl;
	@Autowired 
	private TituloImpl tituloImpl;	
	@Autowired 
	private TreinoImpl treinoImpl;
	

	@RequestMapping(method = RequestMethod.GET, path = "/montarTreino")
	public ModelAndView montarTreino() {		
		 
		 List<Titulo> titulos = this.tituloImpl.listar();
		 List<TreinoExercicio> treinoExercicio = this.treinoExercicioImpl.listar();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 
		 ModelAndView mv = new ModelAndView("treino/form-treino");
		 Treino treino = treinoExercicio.get(0).getTreino();
		 mv.addObject("treinoExercicio",treinoExercicio);
		 mv.addObject("titulos",titulos);		 
		 mv.addObject("treino",treino);		 		 
		 mv.addObject("data",df.format(treino.getData().getTime()));
		 		 
		List<Exercicio> exercicios = new ArrayList<Exercicio>();
		for (Iterator<TreinoExercicio> iterator = treinoExercicio.iterator(); iterator.hasNext();) {
			TreinoExercicio t =  iterator.next(); 
			exercicios.add(t.getExercicio());
		}		
		mv.addObject("exercicios",exercicios);
		 return mv;
	}
	
	@RequestMapping("/listarTreino")
	public String listarTreino() {
		
		return "sobre";
	}
	
		
	
}
