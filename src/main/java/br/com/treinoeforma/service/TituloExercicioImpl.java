package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.TituloExercicio;
import br.com.treinoeforma.repository.TituloExercicioRepository;

@Service
public class TituloExercicioImpl implements Crud<TituloExercicio> {
	
	@Autowired
	private TituloExercicioRepository tituloExercicioRepository;
	
	
	@Override
	public TituloExercicio salvar(TituloExercicio t) {		
		return this.tituloExercicioRepository.save(t);
	}

	@Override
	public void excluir(Long id) {
		this.tituloExercicioRepository.delete(id);		
	}

	@Override
	public List<TituloExercicio> listar() {		
		return this.tituloExercicioRepository.findAll();
	}

	@Override
	public TituloExercicio buscar(Long id) {		 
		return this.tituloExercicioRepository.findOne(id);
	}

	@Override
	public Page<TituloExercicio> buscarPaginando(Pageable pageable) {		 
		return this.tituloExercicioRepository.findAll(pageable);
	}
	

}
