package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.model.TreinoExercicioDTO;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio,Long> {
		
	
	@Query("select te from TreinoExercicio te where te.treino.usuario.id = :codigoUsuario group by te.treino order by te desc  ")
	public List<TreinoExercicio> listarTreinoExercicioAgrupado(@Param("codigoUsuario") Long codigoUsuario);
	
	@Query("select te from TreinoExercicio te group by te.titulo")
	public List<TreinoExercicio> listarTituloAgrupado();
	
	@Query("select te from TreinoExercicio te where te.treino.id = :codigo and te.treino.usuario.id = :codigoUsuario")
	public List<TreinoExercicio> buscaTreinoPorCodigo(@Param("codigo") Long codigo,@Param("codigoUsuario") Long codigoUsuario);
	
	@Query("SELECT NEW br.com.treinoeforma.model.TreinoExercicioDTO(te.treino, te.exercicio, te.titulo, MAX(te.titulo.id)) FROM TreinoExercicio te " + 
			"WHERE te.treino.id = :codigoTreino GROUP BY te.treino")
	public List<TreinoExercicioDTO> buscaUltimoTituloTreino(@Param("codigoTreino") Long codigoTreino);
	

}
