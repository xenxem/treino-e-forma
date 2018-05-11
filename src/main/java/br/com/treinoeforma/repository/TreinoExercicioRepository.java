package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.TreinoExercicio;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio,Long> {
	
	
	@Query("select te from TreinoExercicio te group by te.treino order by te.id desc  ")
	public List<TreinoExercicio> listarTreinoExercicioAgrupado();
	
	@Query("select te from TreinoExercicio te group by te.titulo.id")
	public List<TreinoExercicio> listarTituloAgrupado();

}
