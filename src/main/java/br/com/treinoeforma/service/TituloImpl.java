package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.dto.TituloDTO;
import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;
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
	
	 	
	public List<Titulo> buscaTitulosPorTreino(Long codigo){
		return this.tituloRepository.buscaTitulosPorTreino(codigo);
	}
	
	public List<Exercicio> buscaExerciciosPorTitulo(Long codigoTreino, Long codigoTitulo){
		return this.tituloRepository.buscaExerciciosPorTitulo(codigoTreino, codigoTitulo);
	}
	
	public List<Titulo> listaTitulosDoTreino(Long codigoUsuario){
		return this.tituloRepository.listaTitulosDoTreino(codigoUsuario);
	}
	
	public List<Titulo> buscaExerciciosPorTreino( Long codigo){
		return this.tituloRepository.buscaExerciciosPorTreino(codigo);
	}

	@Override
	public Page<Titulo> listarPorUsuario(Long id, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Titulo alterar(Titulo t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TituloDTO> listaTituloComQuantidadeExercicio(Treino treino){
		return this.tituloRepository.listaTituloComQuantidadeExercicio(treino);
	}
	
	
	public List<Object[]> obterTitulosComQtdDeExercicio(Long treino_id){
		return this.tituloRepository.obterTitulosComQtdDeExercicio(treino_id);
	}
	
	
	
}
