package br.com.treinoeforma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.GrupoMuscular;

@Repository
public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {
	
	@Query("SELECT g FROM GrupoMuscular g ORDER BY g.nome")
	public List<GrupoMuscular> listaPorNome();

}
