package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.model.TreinoExercicioDTO;
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

	
	public List<TreinoExercicio> listarTreinoExercicioAgrupado(Long codigoUsuario) {
		return this.treinoExercicioRepository.listarTreinoExercicioAgrupado(codigoUsuario);
	}	

	@Override
	public TreinoExercicio buscar(Long id) {		
		return this.treinoExercicioRepository.findOne(id);
	}

	@Override
	public Page<TreinoExercicio> buscarPaginando(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TreinoExercicio> listaTituloAgrupado(){
		return this.treinoExercicioRepository.listarTituloAgrupado();
	}

	@Override
	public List<TreinoExercicio> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TreinoExercicio> bucaTreinoPorCodigo(Long codigo,Long codigoUsuario){
		return this.treinoExercicioRepository.buscaTreinoPorCodigo(codigo,codigoUsuario);
	}
	
	public List<TreinoExercicioDTO> buscaUltimoTituloTreino(Long codigoTreino){
		return this.treinoExercicioRepository.buscaUltimoTituloTreino(codigoTreino);
	}

	@Override
	public Page<TreinoExercicio> listarPorUsuario(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void salvarTodos() {
		
	}
			
}
