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
import br.com.treinoeforma.model.TreinoExercicioValuesObject;
import br.com.treinoeforma.security.GpUserDetails;
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
	
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/treinos")
	public ModelAndView treinos() {		
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 ModelAndView mv = new ModelAndView("treino/form-treino");
		
		 GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();		 		 
		 Long codigoUltimoTreino = this.treinoImpl.buscaUltimo(usuarioAutenticado.getId());
		 
		 if (codigoUltimoTreino != null) {
			 
			 List<TreinoExercicioValuesObject> teste = this.treinoExercicioImpl.buscaUltimoTituloTreino(codigoUltimoTreino);
			 
			 for (TreinoExercicioValuesObject t : teste)
				 	System.out.println(t.getExercicio().getId());
			 
			 List<Titulo> titulosDoTreino = tituloImpl.buscaTitulosPorTreino(codigoUltimoTreino);
			 List<Titulo> exerciciosPorTitulo = tituloImpl.buscaExerciciosPorTreino(codigoUltimoTreino);			 
			 List<TreinoExercicio> listaTe = this.treinoExercicioImpl.listarTreinoExercicioAgrupado(usuarioAutenticado.getId());
			 
			 Treino treino = new Treino();
			 Titulo titulo = new Titulo();
			 
			 if (!exerciciosPorTitulo.isEmpty()) { 
				 treino = listaTe.get(0).getTreino();
				 titulo = listaTe.get(0).getTitulo();
			 }
			 		 
			 mv.addObject("treino",treino);
			 mv.addObject("titulo",titulo);
			 mv.addObject("listaTe",listaTe);		 
			 mv.addObject("titulosDoTreino",titulosDoTreino);
			 mv.addObject("exerciciosPorTitulo",exerciciosPorTitulo);
			 mv.addObject("data",df.format(treino.getData().getTime()));
			 
			 return mv;
			 
		 }
		 
		 mv.addObject("treino",new Treino());
		 mv.addObject("titulo",new Titulo());
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
