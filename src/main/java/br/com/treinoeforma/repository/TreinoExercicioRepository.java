package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;
import br.com.treinoeforma.model.TreinoExercicio;
import br.com.treinoeforma.model.TreinoExercicioDTO;
import br.com.treinoeforma.model.Usuario;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio,Long> {
		
	
	@Query("SELECT te FROM TreinoExercicio te "
			+ "WHERE te.treino.usuario.id = :usuarioId "
			+ "GROUP BY te.treino ORDER BY te DESC  ")
	public List<TreinoExercicio> listarTreinoExercicioAgrupado(@Param("usuarioId") Long usuarioId);
	
	@Query("SELECT te FROM TreinoExercicio te "
			+ "GROUP BY te.titulo")
	public List<TreinoExercicio> listarTituloAgrupado();
	
	@Query("SELECT te FROM TreinoExercicio te "
			+ "WHERE te.treino.id = :treinoId "
			+ "AND te.treino.usuario.id = :usuarioId "
			+ "ORDER BY te,te.titulo,te.exercicio")
	public List<TreinoExercicio> buscaTreinoPorCodigo(@Param("treinoId") Long treinoId,@Param("usuarioId") Long usuarioId);
	
	@Query("SELECT NEW br.com.treinoeforma.model.TreinoExercicioDTO(te.treino, te.exercicio, te.titulo, MAX(te.titulo.id)) "
			+ "FROM TreinoExercicio te " + 
			"WHERE te.treino.id = :treinoId "
			+ "GROUP BY te.treino")
	public List<TreinoExercicioDTO> buscaUltimoTituloTreino(@Param("treinoId") Long treinoId);
	
	@Query("SELECT te FROM TreinoExercicio te "
			+ "LEFT JOIN te.exercicio "
			+ "WHERE te.treino = :treino "
			+ "AND te.titulo = :titulo")
	public List<TreinoExercicio> buscaExercicioPorDia(@Param("treino") Treino treino, @Param("titulo") Titulo titulo);
	
	
	@Query("SELECT te FROM TreinoExercicio te "
			+ "LEFT JOIN te.exercicio "
			+ "WHERE te.treino = :treino "
			+ "AND te.titulo = :titulo")
	public List<TreinoExercicio> buscaPorTreinoTitulo(@Param("treino") Treino treino, @Param("titulo") Titulo titulo);
	
	@Query("SELECT MAX(t.id) FROM TreinoExercicio te "
			+ "INNER JOIN te.treino t "
			+ "WHERE t.usuario = :usuario")
	public Long buscaUltimo(@Param("usuario") Usuario usuario);
	
	

}
