package br.com.treinoeforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.repository.ExercicioRepository;

@Service
public class ExercicioImpl implements Crud<Exercicio> {
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	
	@Override
	public Exercicio salvar(Exercicio e) {	
		//ligando grupo muscular e exercício
		e.getGrupoMuscular().getExercicios().add(e);
		
		//alteração ou inclusão?
		if (e.getId() != null && this.exercicioRepository.exists(e.getId())) {			
			this.exercicioRepository
					.updateExercicio(e.getId(), 
							e.getDescricao(), 
							e.getGrupoMuscular().getId());			
		}else 
			e = this.exercicioRepository.save(e);		
		return e;		
	}

	@Override
	public void excluir(Long id) {
		this.exercicioRepository.delete(id);		
	}
	

	@Override
	public List<Exercicio> listar() {
		return this.exercicioRepository.findAll();
	}

	@Override
	public Exercicio buscar(Long id) {		
		return this.exercicioRepository.findOne(id);
	}
		
	public List<Exercicio> listarPorNome(String descricao) {		
		return this.exercicioRepository.listarPorNome(descricao);
	}

	@Override
	public Page<Exercicio> buscarPaginando(Pageable pageable) {
		return this.exercicioRepository.findAll(pageable);
	}


	
	public List<Exercicio> buscaExerciciosPorTitulo(Long codigoTreino, Long codigoTitulo){
		return this.exercicioRepository.buscaExerciciosPorTitulo(codigoTreino, codigoTitulo);
	}
	
	public List<Exercicio> buscaExerciciosPorTreino(Long codigoUsuario, Long codigoTreino){
		return this.exercicioRepository.buscaExerciciosPorTreino(codigoUsuario, codigoTreino);
	}
	
}
