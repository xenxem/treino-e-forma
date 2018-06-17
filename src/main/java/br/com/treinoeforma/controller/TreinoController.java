package br.com.treinoeforma.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import br.com.treinoeforma.model.TreinoExercicioDTO;
import br.com.treinoeforma.security.GpUserDetails;
import br.com.treinoeforma.service.ExercicioImpl;
import br.com.treinoeforma.service.TituloImpl;
import br.com.treinoeforma.service.TreinoExercicioImpl;
import br.com.treinoeforma.service.TreinoImpl;
import br.com.treinoeforma.utils.UsuarioAutenticado;

@Controller
@Transactional
public class TreinoController {
	
	
	@Autowired 
	private TreinoExercicioImpl treinoExercicioImpl;
	@Autowired 
	private TituloImpl tituloImpl;
	@Autowired 
	private TreinoImpl treinoImpl;
	@Autowired 
	private ExercicioImpl exercicioImpl;
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/treinos")
	public ModelAndView treinos() {		
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 
		
		 GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();		 		 
		 Long codigoUltimoTreino = this.treinoImpl.buscaUltimo(usuarioAutenticado.getId());
		 
		 Exercicio exercicio = new Exercicio();
		 
		 ModelAndView mv = new ModelAndView("treino/form-treino");
		 
		 if (codigoUltimoTreino != null) {
			 
			 List<TreinoExercicioDTO> listaUltimoTitulo = this.treinoExercicioImpl.buscaUltimoTituloTreino(codigoUltimoTreino);			 
			 Long codigoTitulo = listaUltimoTitulo.get(0).getUltimo();					 
			 
			 List<Titulo> titulosDoTreino = tituloImpl.buscaTitulosPorTreino(codigoUltimoTreino);
			 List<Exercicio> listaExercicios =this.exercicioImpl.buscaExerciciosPorTitulo(codigoUltimoTreino, codigoTitulo);
			 List<TreinoExercicio> listaTe = this.treinoExercicioImpl.listarTreinoExercicioAgrupado(usuarioAutenticado.getId());
			 
			 Treino treino = this.treinoImpl.buscar(codigoUltimoTreino);				
			 Titulo titulo = tituloImpl.buscar(codigoTitulo);
			 		 
			 mv.addObject("treino",treino);
			 mv.addObject("titulo",titulo);
			 mv.addObject("listaTe",listaTe);
			 mv.addObject("exercicio",exercicio);
			 mv.addObject("titulosDoTreino",titulosDoTreino);
			 mv.addObject("listaExercicios",listaExercicios);
			 mv.addObject("data",df.format(treino.getData().getTime()));
			 
			 return mv;
			 
		 }
		 
		 mv.addObject("treino",new Treino());
		 mv.addObject("titulo",new Titulo());
		 mv.addObject("exercicio",exercicio);
		 mv.addObject("listaTe",new ArrayList<TreinoExercicio>());		 
		 mv.addObject("titulosDoTreino",new ArrayList<Titulo>());
		 mv.addObject("exerciciosPorTitulo",new ArrayList<Exercicio>());
		 mv.addObject("data","");
		
		 return mv;
	}
	
	@RequestMapping("/listarTreino")
	public String listarTreino() {
		
		return "sobre";
	}
	
		
	
}
