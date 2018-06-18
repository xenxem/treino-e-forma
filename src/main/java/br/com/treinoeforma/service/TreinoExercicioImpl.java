package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.model.TreinoExercicioDTO;
import br.com.treinoeforma.repository.TreinoExercicioRepository;

@Service
public class TreinoExercicioImpl implements Crud<TreinoExercicio> {
	
	@Autowired
	private TreinoExercicioRepository treinoExercicioRepository;

	@Override
	public TreinoExercicio salvar(TreinoExercicio t) {
		return this.treinoExercicioRepository.save(t);
	}

	@Override
	public void excluir(Long id) {
		this.treinoExercicioRepository.delete(id);		
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
	
	public List<TreinoExercicio> buscaTreinoPorCodigo(Long codigo,Long codigoUsuario){
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
		
	public List<TreinoExercicio> buscaExercicioPorDia(@Param("treino") Treino treino, @Param("titulo") Titulo titulo){
		return this.treinoExercicioRepository.buscaExercicioPorDia(treino, titulo);
	}

	@Override
	public TreinoExercicio alterar(TreinoExercicio t) {
		// TODO Auto-generated method stub 
		
		return null;
	}
	
	public List<TreinoExercicio> buscaPorTreinoTitulo(Treino treino,Titulo titulo){
		return this.treinoExercicioRepository.buscaPorTreinoTitulo(treino, titulo);
	}
	
	
	
			
}
