package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.TreinoExercicio;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicio,Long> {

}
