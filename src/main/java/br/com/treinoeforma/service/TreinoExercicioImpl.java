package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.repository.TreinoExercicioRepository;

@Service
public class TreinoExercicioImpl implements Crud<TreinoExercicio> {
	
	@Autowired
	private TreinoExercicioRepository treinoExercicioRepository;

	@Override
	public TreinoExercicio salvar(TreinoExercicio t) {
		return null;
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TreinoExercicio> listar() {
		return this.treinoExercicioRepository.findAll();
	}

	@Override
	public TreinoExercicio buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TreinoExercicio> buscarPaginando(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
