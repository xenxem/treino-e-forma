package br.com.treinoeforma.repository;


import java.util.List;

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
}
