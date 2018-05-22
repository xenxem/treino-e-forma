package br.com.treinoeforma.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.GrupoMuscular;
import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.model.TreinoExercicioDTO;
import br.com.treinoeforma.security.GpUserDetails;
import br.com.treinoeforma.service.ExercicioImpl;
import br.com.treinoeforma.service.GrupoMuscularImpl;
import br.com.treinoeforma.service.TituloImpl;
import br.com.treinoeforma.service.TreinoExercicioImpl;
import br.com.treinoeforma.service.TreinoImpl;
import br.com.treinoeforma.utils.UsuarioAutenticado;

@Controller
@Transactional
public class TreinoExercicioController {
		
	@Autowired 
	private ExercicioImpl exercicioImpl;	
	@Autowired 
	private GrupoMuscularImpl grupoMuscularImpl;	
	@Autowired 
	private TreinoExercicioImpl treinoExercicioImpl;	
	@Autowired 
	private TituloImpl tituloImpl;	
	@Autowired 
	private TreinoImpl treinoImpl;	
	
	@RequestMapping(method = RequestMethod.GET, path="/montar")
	public ModelAndView mostrar(@RequestParam("codigoTreino") Long codigoTreino, @RequestParam("codigoTitulo") Long codigoTitulo){
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 ModelAndView mv = new ModelAndView("treino/form-treino");
		 
		 GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();
		 
		 if (codigoTreino == null) 
			 		codigoTreino = treinoImpl.buscaUltimo(usuarioAutenticado.getId());
		 
		 List<TreinoExercicioDTO> listaUltimoTitulo = this.treinoExercicioImpl.buscaUltimoTituloTreino(codigoTreino);
		 
		 if (codigoTitulo == null)
			 codigoTitulo = listaUltimoTitulo.get(0).getUltimo();
		 
		 List<Titulo> titulosDoTreino = tituloImpl.buscaTitulosPorTreino(codigoTreino);
		 List<TreinoExercicio> listaTe = this.treinoExercicioImpl.listarTreinoExercicioAgrupado(usuarioAutenticado.getId());
		 List<Exercicio> listaExercicios = (codigoTitulo == null) ? 
				 this.exercicioImpl.buscaExerciciosPorTreino(usuarioAutenticado.getId(), codigoTreino) 
				 : exercicioImpl.buscaExerciciosPorTitulo(codigoTreino, codigoTitulo);
		 
		 Treino treino = this.treinoImpl.buscar(codigoTreino);
		 Titulo titulo;
		 
		 if (codigoTitulo == null) {titulo = listaTe.get(0).getTitulo();}
		 else { titulo = this.tituloImpl.buscar(codigoTitulo);}
		 
		 String dataFormatada = df.format(treino.getData().getTime());
		 		 		 
		 mv.addObject("treino",treino);
		 mv.addObject("titulo",titulo);		 
		 mv.addObject("listaTe",listaTe);
		 mv.addObject("data",dataFormatada);		 
		 mv.addObject("titulosDoTreino",titulosDoTreino);
		 mv.addObject("listaExercicios",listaExercicios);
		 
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/salvar")
	public ModelAndView salvar(HttpServletRequest request) throws ParseException {
		
		String data = request.getParameter("data");
		String descricao = request.getParameter("descricao");		
		String codigosSelecionados = request.getParameter("codigosSelecionados");
		
		Treino t = new Treino();
		t.setDescricao(descricao);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(data));
		t.setData(c);
		
		String[] listaDeCodigos = codigosSelecionados.split(",");
		List<TreinoExercicio> treinoExercicioLista = new ArrayList<TreinoExercicio>();		
		
		for (int i = 0; i < listaDeCodigos.length; i++) {						
			Exercicio e = new Exercicio();
			e.setId(Long.parseLong(listaDeCodigos[i]));			
			TreinoExercicio te = new TreinoExercicio();
			te.setExercicio(e);			
			treinoExercicioLista.add(te);			
		}
		
		GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();
		
		List<Titulo> titulos = this.tituloImpl.listar();
		
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("descricao",descricao);
		mv.addObject("data",data);
		mv.addObject("titulos",titulos);		
		mv.addObject("codigosSelecionados",codigosSelecionados);	
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/novo")
	public ModelAndView novo(HttpServletRequest request) throws ParseException {
		
		//String codigoExercicio = request.getParameter("codigoExercicio");		
		//List<String> lista = Arrays.asList(hidExercicio.split(","));
		
		String data = ""; 
		String descricao = "";		
		String codigosSelecionados = "";
		
		List<Titulo> titulos = this.tituloImpl.listar();
		
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("descricao",descricao);
		mv.addObject("data",data);
		mv.addObject("titulos",titulos);		
		mv.addObject("codigosSelecionados",codigosSelecionados);	
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/alterar")
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
	
	@RequestMapping(method = RequestMethod.GET, path="/excluir")
	public ModelAndView excluir() {
		
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("treino/form-seleciona-exercicio");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);		
		mv.addObject(new Exercicio());
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path="/pdf")
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
