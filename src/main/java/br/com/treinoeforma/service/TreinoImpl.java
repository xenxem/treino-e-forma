package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.repository.TreinoRepository;

@Service
public class TreinoImpl implements Crud<Treino> {
	
	
	@Autowired
	private TreinoRepository treinoRepository;
	

	@Override
	public Treino salvar(Treino t) {		
		return null;
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Treino> listar() {		
		return this.treinoRepository.findAll();
	}

	@Override
	public Treino buscar(Long id) {		
		return this.treinoRepository.findOne(id);
	}

	@Override
	public Page<Treino> buscarPaginando(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long buscaUltimo(Long codigoUsario) {
		return this.treinoRepository.buscaUltimo(codigoUsario);
	}

	@Override
	public Page<Treino> listarPorUsuario(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
