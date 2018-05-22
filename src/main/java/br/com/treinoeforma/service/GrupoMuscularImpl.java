package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.GrupoMuscular;
import br.com.treinoeforma.repository.GrupoMuscularRepository;

@Service
public class GrupoMuscularImpl implements Crud<GrupoMuscular> {

	
	@Autowired
	private GrupoMuscularRepository grupoMuscularRepository;
	@Override
	public GrupoMuscular salvar(GrupoMuscular t) {		
		return null;
	}

	@Override
	public void excluir(Long id) {
		this.grupoMuscularRepository.delete(id);		
	}

	@Override
	public List<GrupoMuscular> listar() {		
		return this.grupoMuscularRepository.findAll();
	}

	@Override
	public GrupoMuscular buscar(Long id) {		
		return this.grupoMuscularRepository.findOne(id);
	}

	@Override
	public Page<GrupoMuscular> buscarPaginando(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GrupoMuscular> listarPorUsuario(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
