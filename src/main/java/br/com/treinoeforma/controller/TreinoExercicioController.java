package br.com.treinoeforma.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treinoeforma.dto.TreinoExercicioDTO;
import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.GrupoMuscular;
import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.model.Usuario;
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
	
	@RequestMapping(method = RequestMethod.POST, path="/montar")
	public ModelAndView mostrar(Long treinoId, Long tituloId){
					
		 
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 ModelAndView mv = new ModelAndView("treino/form-treino");
		 
		 GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();
		 
		 
		 
		 if (treinoId == null) 
			 		treinoId = treinoImpl.buscaUltimo(usuarioAutenticado.getId());
		 
		 
		 
		 List<TreinoExercicioDTO> listaUltimoTitulo = this.treinoExercicioImpl.buscaUltimoTituloTreino(treinoId);
		 
		 if (tituloId == null)
			 tituloId = listaUltimoTitulo.get(0).getUltimo();
		 
		 List<Titulo> titulosDoTreino = tituloImpl.listar();
		 List<TreinoExercicio> listaTe = this.treinoExercicioImpl.listarTreinoExercicioAgrupado(usuarioAutenticado.getId());		 
		 List<Exercicio> exerciciosList = this.exercicioImpl.listar();
		 List<GrupoMuscular> grupos = this.grupoMuscularImpl.listar();
		 
		 Treino treino = this.treinoImpl.buscar(treinoId);
		 Titulo titulo = new Titulo();
		 titulo.setId(tituloId);		
		 		 		 
		 if (tituloId == null){
			 titulo = listaTe.get(0).getTitulo();}
		 else{ 
			 titulo = this.tituloImpl.buscar(tituloId);
		 }		 
		 List<TreinoExercicio> listaTePorDia = this.treinoExercicioImpl.buscaPorTreinoTitulo(treino, titulo);
				 
		 String dataFormatada = df.format(treino.getData().getTime());
		 		 		 
		 mv.addObject("treino",treino);
		 mv.addObject("titulo",titulo);		 
		 mv.addObject("listaTe",listaTe);		 
		 mv.addObject("grupos",grupos);
		 mv.addObject("listaTePorDia",listaTePorDia);
		 mv.addObject("exercicio",new Exercicio());
		 mv.addObject("data",dataFormatada);		 
		 mv.addObject("titulosDoTreino",titulosDoTreino);
		 mv.addObject("exerciciosList",exerciciosList);
		 
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/salvarTreino")
	public ModelAndView salvar(HttpServletRequest request,TreinoExercicio te) throws ParseException {
		
		GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(request.getParameter("data")));		
		
		Treino treino = te.getTreino();
		treino.setData(c);
		
		Usuario usuario = new Usuario();
		usuario.setId(usuarioAutenticado.getId());
		treino.setUsuario(usuario);			
		treino = this.treinoImpl.salvar(treino);		
		te.setTreino(treino);		
		te = this.treinoExercicioImpl.salvar(te);
		
		List<TreinoExercicio> listaTe = this.treinoExercicioImpl.buscaTreinoPorCodigo(treino.getId(), usuarioAutenticado.getId());
		List<Exercicio> exercicios = exercicioImpl.listar();
		List<Titulo> titulosDoTreino = this.tituloImpl.buscaTitulosPorTreino(treino.getId());
		List<GrupoMuscular> grupos = this.grupoMuscularImpl.listar();
		
		List<Titulo> titulos = this.tituloImpl.listar();
		 
		ModelAndView mv = new ModelAndView("treino/form-exercicio");		 
		mv.addObject("titulos",titulos);
		mv.addObject("listaTe",listaTe);
		mv.addObject("grupos",grupos);
		mv.addObject("treinoExercicio",te);
		mv.addObject("exercicios",exercicios);		
		mv.addObject("titulosDoTreino",titulosDoTreino);
		return mv;
		 
		 
		/*
		String data = request.getParameter("data");
		String tituloId = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");		
		String codigoTreino = request.getParameter("codigoTreino");
		String codigosSelecionados = request.getParameter("codigosSelecionados");
				
		Treino treino = new Treino();
		Usuario u = new Usuario();
				
		u.setId(usuarioAutenticado.getId());		
		treino.setUsuario(u);
		treino.setCurtidas(0);
		treino.setDescricao(descricao);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(data));
		treino.setData(c);		
		
		Titulo titulo = new Titulo();
		titulo.setId(Long.parseLong(tituloId));				
		String[] listaDeIds = codigosSelecionados.split(",");
		Long[] lista1 = new Long[listaDeIds.length];
		
		for (int i=0; i < listaDeIds.length; i++)
			lista1[i] = Long.parseLong(listaDeIds[i]);
		Arrays.sort(lista1);
		
		List<Titulo> titulos = this.tituloImpl.listar();
		
		ModelAndView mv;
				
		//Indo para alterar
		if (codigoTreino != null) {
			treino.setId(Long.parseLong(codigoTreino));			
			mv = new ModelAndView("forward:alterar");
			mv.addObject("descricao",descricao);
			mv.addObject("data",data);
			mv.addObject("titulos",titulos);
			mv.addObject("treino",treino);
			mv.addObject("codigosSelecionados",codigosSelecionados);	
			return mv;
		}
		
		treino = this.treinoImpl.salvar(treino);
		
		for (int i = 0; i < lista1.length; i++) {			
			TreinoExercicio te = new TreinoExercicio();			
			te.setTreino(treino);
			te.setTitulo(titulo);			
			Exercicio exercicio  = new Exercicio();
			exercicio.setId(lista1[i]);
			te.setExercicio(exercicio);
			this.treinoExercicioImpl.salvar(te);
		}
		
				
		List<Exercicio> lista2 = exercicioImpl.buscaExerciciosPorTitulo(treino.getId(),titulo.getId());
				
		int i=0;
		String[] listaIds = new String[listaDeIds.length];
		
		for (Iterator<Exercicio> iterator = lista2.iterator(); iterator.hasNext();) {
			Exercicio exercicio = (Exercicio) iterator.next();
			listaIds[i] = exercicio.getId().toString();
			i++;
		}
				
		codigosSelecionados = Arrays.stream(listaDeIds).collect(Collectors.joining(","));
		
		mv = new ModelAndView("treino/form-exercicio");
		mv.addObject("descricao",descricao);
		mv.addObject("data",data);
		mv.addObject("titulos",titulos);		
		mv.addObject("treino",treino);
		mv.addObject("codigosSelecionados",codigosSelecionados);
		*/	
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/novo")
	public ModelAndView novo(HttpServletRequest request) throws ParseException {
		
		//String codigoExercicio = request.getParameter("codigoExercicio");		
		//List<String> lista = Arrays.asList(hidExercicio.split(","));
		
		TreinoExercicio te = new TreinoExercicio();
		Treino treino = new Treino();
		Titulo titulo = new Titulo();
		titulo.setId(1l);
		te.setTitulo(titulo);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		treino.setData(c);
		te.setTreino(treino);
				
		List<Titulo> titulos = this.tituloImpl.listar();
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupos = this.grupoMuscularImpl.listar();
		
		ModelAndView mv = new ModelAndView("treino/form-exercicio");
		mv.addObject("grupos",grupos);
		mv.addObject("titulos",titulos);
		mv.addObject("treinoExercicio",te);
		mv.addObject("exercicios",exercicios);		
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, path="/editarTE")
	public @ResponseBody String editarTE(Long id,Long exercicioId,Long treinoId,Long tituloId, HttpServletResponse response) throws JsonProcessingException {
		Treino tr = new Treino();
		Titulo ti = new Titulo();
		Exercicio ex = new Exercicio();
		TreinoExercicio te = new TreinoExercicio();	
		
		te = this.treinoExercicioImpl.buscar(id);
		ex = this.exercicioImpl.buscar(exercicioId);
		
		ex.setId(exercicioId);
		tr.setId(treinoId);
		ti.setId(tituloId);
		
		
		te.setExercicio(ex);
		te.setTreino(tr);
		te.setTitulo(ti);
		
		//response.setStatus(200);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(te);		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/pdf")
	public ModelAndView pdf() {
		
		List<Exercicio> exercicios = this.exercicioImpl.listar();
		List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("treino/form-exercicio");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);		
		mv.addObject(new Exercicio());
		return mv;
	}
	
	
	@RequestMapping("/excluirTE")
	public void excluirTE(Long id, HttpServletResponse response) {		
		this.treinoExercicioImpl.excluir(id);		
		response.setStatus(200);
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/salvarTE")
	public @ResponseBody String salvarTE(Long exercicioId,Long treinoId,Long tituloId, HttpServletResponse response) throws JsonProcessingException {
		Treino tr = new Treino();
		Titulo ti = new Titulo();
		Exercicio ex = new Exercicio();
		TreinoExercicio te = new TreinoExercicio();
		ex = this.exercicioImpl.buscar(exercicioId);		
		ex.setId(exercicioId);
		tr.setId(treinoId);
		ti.setId(tituloId);		
		te.setExercicio(ex);
		te.setTreino(tr);
		te.setTitulo(ti);
		this.treinoExercicioImpl.salvar(te);
		//response.setStatus(200);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(te);		
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/excluirExercicioTreino")
	public String excluirExercicioTreino() {
		
		return "redirect:treinos";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/editarExercicioTreino")
	public String editarExercicioTreino() {		
		return "redirect:treinos";
	}
	

}
