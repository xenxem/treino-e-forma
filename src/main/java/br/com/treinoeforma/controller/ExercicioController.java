package br.com.treinoeforma.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.GrupoMuscular;
import br.com.treinoeforma.security.GpUserDetails;
import br.com.treinoeforma.service.ExercicioImpl;
import br.com.treinoeforma.service.GrupoMuscularImpl;
import br.com.treinoeforma.utils.PageWrapper;
import br.com.treinoeforma.utils.UsuarioAutenticado;


@Controller
@Transactional
public class ExercicioController {
	
	@Autowired 
	private ExercicioImpl exercicioImpl;	
	@Autowired 
	private GrupoMuscularImpl grupoMuscularImpl;


	
	@RequestMapping(method = RequestMethod.POST, path ="/salvar")
	public String salvar(Exercicio exercicio) {		
		this.exercicioImpl.salvar(exercicio);				
		return "redirect:/listarExercicio";
	}
		
	
	@RequestMapping(method = RequestMethod.GET, path ="/listaExercicioXHR")
	public @ResponseBody String obterLista(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{	
		
		GpUserDetails usuarioAutenticado = (GpUserDetails) UsuarioAutenticado.obterUsuarioAutenticado();
		
		String pCodigoTreino = request.getParameter("pCodigoTreino");
		String pTitulo = request.getParameter("pTitulo");
		
		List<Exercicio> exercicios;
		
		if (pCodigoTreino == null)			
			exercicios = this.exercicioImpl.listar();
		else {			
			exercicios = this.exercicioImpl.buscaExerciciosPorTreino(usuarioAutenticado.getId(),3l);
			exercicios = exercicioImpl.buscaExercicioNaoCadastrado(exercicios);			
		}
			
		ObjectMapper mapper = new ObjectMapper();			
		
		//User user = new User("Harrison", "Ford");
		//user.setEmailAddrs(Arrays.asList("harrison@example.com"));		
		//mapper.writeValue(System.out, user);
				
		return mapper.writeValueAsString(exercicios);
	}
		
	 @RequestMapping(method = RequestMethod.POST, path="/listaExercicioXHRAutoComplete")
	 public @ResponseBody List<Exercicio> listaExercicioAjax(String term) throws JsonProcessingException{		 
		  List<Exercicio> exercicios = this.exercicioImpl.listarPorNome(term); 
		  return exercicios;
	 }
	
	 @RequestMapping(method = RequestMethod.GET, path="/listarExercicio")
	 public ModelAndView listaExercicios(Pageable pageable) {	
	 	 
		 GpUserDetails usuarioAutenticado = UsuarioAutenticado.obterUsuarioAutenticado();		 
		 PageWrapper<Exercicio> page = new PageWrapper<>(this.exercicioImpl.listarPorUsuario(usuarioAutenticado.getId(), pageable),"/listarExercicio");
		 
		 List<Exercicio> exercicios = page.getContent();		 
		 List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		 
		 ModelAndView mv = new ModelAndView("exercicio/form");
		 mv.addObject("exercicios",exercicios);
		 mv.addObject("grupoMuscular",grupoMuscular);		
		 mv.addObject("page",page);
		 mv.addObject(new Exercicio());
		 return mv;
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, path="/editar/{id}")
	 public ModelAndView editar(@PathVariable Long id) {	   
	   	Exercicio exercicio = this.exercicioImpl.buscar(id);
	   	List<Exercicio> exercicios = this.exercicioImpl.listar();
	    List<GrupoMuscular> grupoMuscular = this.grupoMuscularImpl.listar();
		ModelAndView mv = new ModelAndView("exercicio/form");
		mv.addObject("exercicios",exercicios);
		mv.addObject("grupoMuscular",grupoMuscular);
		mv.addObject("exercicio",exercicio);
		return mv;
	 }
	   
	 @RequestMapping(method = RequestMethod.GET, path="/editarXHR")
	 public @ResponseBody String edita(Long id) throws JsonProcessingException {	   
	   	Exercicio exercicio = this.exercicioImpl.buscar(id);
	   	ObjectMapper mapper = new ObjectMapper();
	   	return mapper.writeValueAsString(exercicio);
	 }
	   
	 @RequestMapping(method = RequestMethod.GET, path = "/excluir/{id}")
	 public String excluir(@PathVariable Long id) {
		 this.exercicioImpl.excluir(id);		    		
		 return "redirect:/listarExercicio";	
	 }
	   
	 @RequestMapping(method = RequestMethod.GET, path="/excluirXHR")
	 public @ResponseBody String exclui(Long id) {		
		 this.exercicioImpl.excluir(id);
		 return "ok";
	 }
	  
	 	 
	 
	 
	 
}
