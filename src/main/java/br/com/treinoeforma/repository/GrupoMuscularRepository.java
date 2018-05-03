package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.GrupoMuscular;

@Repository
public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {

}
