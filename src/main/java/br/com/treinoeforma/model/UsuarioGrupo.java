package br.com.treinoeforma.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UsuarioGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	UsuarioGrupoId id;

	public UsuarioGrupo() {}
	
	public UsuarioGrupo(UsuarioGrupoId id) {
		super();
		this.id = id;
	}

	
	
}
