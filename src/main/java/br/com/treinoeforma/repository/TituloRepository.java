package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.dto.TituloDTO;
import br.com.treinoeforma.model.Exercicio;
import br.com.treinoeforma.model.Titulo;
import br.com.treinoeforma.model.Treino;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {
	
	@Query("SELECT te.titulo "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id=:tituloId "
			+ "GROUP BY te.titulo")
	public List<Titulo> buscaTitulosPorTreino(@Param("tituloId") Long tituloId);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id =:treinoId "
			+ "AND te.titulo.id = :tituloId")
	public List<Exercicio> buscaExerciciosPorTitulo(@Param("treinoId") Long treinoId,@Param("tituloId") Long tituloId);
	
	@Query("SELECT te.titulo FROM TreinoExercicio te "
			+ "WHERE te.treino.usuario.id =:usuarioId "
			+ "GROUP BY te.titulo.id "
			+ " ORDER BY te.titulo.id DESC")
	public List<Titulo> listaTitulosDoTreino(@Param("usuarioId") Long usuarioId);
	
	@Query("SELECT te.exercicio "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino.id=:treinoId")
	public List<Titulo> buscaExerciciosPorTreino(@Param("treinoId") Long treinoId);
	
	@Query("SELECT NEW br.com.treinoeforma.dto.TituloDTO(te.titulo,COUNT(te.titulo)) "
			+ "FROM TreinoExercicio te "
			+ "WHERE te.treino=:treino "
			+ "GROUP BY te.titulo "
			+ "ORDER BY te.titulo.descricao")
	public List<TituloDTO> listaTituloComQuantidadeExercicio(@Param("treino") Treino treino);
	
	@Query(value="SELECT * "
			+ "FROM titulo "
			+ "LEFT JOIN (SELECT titulo_id, count(*) "
			+ "FROM treino_exercicio "
			+ "WHERE treino_id = :treino_id "
			+ "GROUP BY titulo_id) tab1 ON tab1.titulo_id = titulo.id",nativeQuery=true)
	public List<Object[]> obterTitulosComQtdDeExercicio(@Param("treino_id") Long treino_id);
	
	
	
}
