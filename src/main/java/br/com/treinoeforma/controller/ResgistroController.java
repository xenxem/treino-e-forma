package br.com.treinoeforma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinoeforma.config.SecurityConfig;
import br.com.treinoeforma.model.Grupo;
import br.com.treinoeforma.model.Usuario;
import br.com.treinoeforma.model.UsuarioGrupo;
import br.com.treinoeforma.model.UsuarioGrupoId;
import br.com.treinoeforma.service.UsuarioGrupoImpl;
import br.com.treinoeforma.service.UsuarioImpl;
import br.com.treinoeforma.utils.UsuarioAutenticado;

@Controller
@Transactional
public class ResgistroController {
	
	@Autowired
	private SecurityConfig sc;
	
	@Autowired
	private UsuarioImpl usuarioImpl;
	
	@Autowired
	private UsuarioGrupoImpl usuarioGrupoImpl;
	
	
	@RequestMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario,RedirectAttributes attributes) {		
		
		String usuarioAutenticado = UsuarioAutenticado.obterUsuarioAutenticado().getNome(); 
		
		
		Usuario u = this.usuarioImpl.buscarPorLogin(usuario.getLogin().toLowerCase());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuarioAutenticado", usuarioAutenticado);
		
		if (u != null) {
			
			mv.setViewName("registro");			
			mv.addObject("mensagem", "Este usuário já existe! Escolha um outro nome de usuário para acesso.");
			
		}else {
			
			mv.setViewName("redirect:login");
			String senha = sc.passwordEncoder().encode(usuario.getSenha());
			usuario.setAtivo(true);
			usuario.setSenha(senha);	
			usuario.setLogin(usuario.getLogin().toLowerCase());
			usuario = usuarioImpl.salvar(usuario);
			
			Grupo grupo = new Grupo();
			grupo.setId(2l);
			
			UsuarioGrupoId id = new UsuarioGrupoId(usuario,grupo);						
			UsuarioGrupo ug = new UsuarioGrupo(id);			
			this.usuarioGrupoImpl.salvar(ug);
						
			attributes.addFlashAttribute("mensagem", "Usuario gravado com sucesso! Digite seu usuário e senha abaixo para acessar a aplicação.");
			attributes.addFlashAttribute("logingravado", usuario.getLogin());
		}
		return mv;
	}
}
