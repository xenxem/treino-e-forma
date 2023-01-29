package br.com.treinoeforma.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioGrupoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="grupo_id")
	private GrupoMuscular grupoMuscular;
	
	
	public UsuarioGrupoId() {}
		
	public UsuarioGrupoId(Usuario usuario, GrupoMuscular grupoMuscular) {
		super();
		this.usuario = usuario;
		this.grupoMuscular = grupoMuscular;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public GrupoMuscular getGrupo() {
		return grupoMuscular;
	}
	public void setGrupo(GrupoMuscular grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupoMuscular == null) ? 0 : grupoMuscular.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioGrupoId other = (UsuarioGrupoId) obj;
		if (grupoMuscular == null) {
			if (other.grupoMuscular != null)
				return false;
		} else if (!grupoMuscular.equals(other.grupoMuscular))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
	
}
