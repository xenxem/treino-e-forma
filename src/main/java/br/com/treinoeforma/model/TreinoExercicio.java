package br.com.treinoeforma.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(TreinoExercicioId.class)
public class TreinoExercicio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Id
	@ManyToOne
	@JoinColumn(name="treino_id")
	private Treino treino;
	
	@Id
	@ManyToOne
	@JoinColumn(name="exercicio_id")
	private Exercicio exercicio;
		
	private String rotulo;	
	private Long ordem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Treino getTreino() {
		return treino;
	}
	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
	
	public Long getOrdem() {
		return ordem;
	}
	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}
	public String getRotulo() {
		return rotulo;
	}
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exercicio == null) ? 0 : exercicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((treino == null) ? 0 : treino.hashCode());
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
		TreinoExercicio other = (TreinoExercicio) obj;
		if (exercicio == null) {
			if (other.exercicio != null)
				return false;
		} else if (!exercicio.equals(other.exercicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (treino == null) {
			if (other.treino != null)
				return false;
		} else if (!treino.equals(other.treino))
			return false;
		return true;
	}
	
	
	
	
	

}
