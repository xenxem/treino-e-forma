package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.UsuarioGrupo;
import br.com.treinoeforma.repository.UsuarioGrupoRepository;

@Service
public class UsuarioGrupoImpl implements Crud<UsuarioGrupo> {

	@Autowired
	private UsuarioGrupoRepository repository;
	
	@Override
	public UsuarioGrupo salvar(UsuarioGrupo t) {		
		return this.repository.save(t);
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioGrupo alterar(UsuarioGrupo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioGrupo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioGrupo buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UsuarioGrupo> buscarPaginando(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UsuarioGrupo> listarPorUsuario(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
