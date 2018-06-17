package br.com.treinoeforma.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.Exercicio;


@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Exercicio e SET e.descricao = :descricao, "
    		+ "e.grupoMuscular.id = :grupomuscular_id "
    		+ "WHERE e.id = :id")
    int updateExercicio(@Param("id") Long id, 
    		@Param("descricao") String descricao, 
    		@Param("grupomuscular_id") Long grupomuscular_id);
		
	@Modifying(clearAutomatically = true)
    @Query("SELECT e FROM Exercicio e"
    		+ " WHERE e.descricao LIKE CONCAT('%',:descricao,'%')")    		
    List<Exercicio> listarPorNome(@Param("descricao") String descricao);	
	
	@Query("SELECT te.exercicio FROM TreinoExercicio te "
			+ "WHERE te.treino.id =:treinoId "
			+ "AND te.titulo.id = :tituloId ")
	public List<Exercicio> buscaExerciciosPorTitulo(@Param("treinoId") Long treinoId,@Param("tituloId") Long tituloId);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.usuario.id =:usuarioId "
			+ "AND te.treino.id =:treinoId")
	public List<Exercicio> buscaExerciciosPorTreino(@Param("usuarioId") Long usuarioId, @Param("treinoId") Long treinoId);
	
	@Query("SELECT e "
			+ "FROM Exercicio e "
			+ "WHERE e.usuario.id = :usuarioId")
	public Page<Exercicio> findById(@Param("usuarioId") Long usuarioId, Pageable pageabel);
	
	@Query("SELECT e "
			+ "FROM Exercicio e "
			+ "WHERE e NOT IN (:exercicios) "
			+ "ORDER BY e.descricao")
	public List<Exercicio> buscaExercicioNaoCadastrado(@Param("exercicios") List<Exercicio> exercicios);
			
}
