package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.Usuario;
import br.com.treinoeforma.repository.UsuarioRepository;

@Service
public class UsuarioImpl implements Crud<Usuario>  {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Override
	public Usuario salvar(Usuario u) {
		return this.usuarioRepository.save(u);
	}

	@Override
	public void excluir(Long id) {
		this.usuarioRepository.delete(id);		
	}

	@Override
	public List<Usuario> listar() {		
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario buscar(Long id) {		
		return this.usuarioRepository.findOne(id);
	}

	@Override
	public Page<Usuario> buscarPaginando(Pageable pageable) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Usuario> listarPorUsuario(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario alterar(Usuario t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario buscarPorLogin(String login) {
		return this.usuarioRepository.findByLogin(login);
	}
		
	

}
