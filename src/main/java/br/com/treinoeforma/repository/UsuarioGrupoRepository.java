package br.com.treinoeforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treinoeforma.model.UsuarioGrupo;
import br.com.treinoeforma.model.UsuarioGrupoId;
@Repository
public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, UsuarioGrupoId> {

		
}
