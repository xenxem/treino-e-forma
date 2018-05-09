package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.repository.TituloRepository;

@Service
public class TituloImpl implements Crud<Titulo> {
	
	@Autowired
	private TituloRepository tituloRepository;
	
	
	@Override
	public Titulo salvar(Titulo t) {		
		return this.tituloRepository.save(t);
	}

	@Override
	public void excluir(Long id) {
		this.tituloRepository.delete(id);		
	}

	@Override
	public List<Titulo> listar() {		
		return this.tituloRepository.findAll();
	}

	@Override
	public Titulo buscar(Long id) {		 
		return this.tituloRepository.findOne(id);
	}

	@Override
	public Page<Titulo> buscarPaginando(Pageable pageable) {		 
		return this.tituloRepository.findAll(pageable);
	}
	

}
