package br.com.treinoeforma.model;

import java.io.Serializable;


public class TreinoExercicioId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Treino treino;	
	private Exercicio exercicio;	
		
	public  TreinoExercicioId() {}
	
	public  TreinoExercicioId(Treino treino,Exercicio exercicio) {
		super();
		this.treino = treino;
		this.exercicio = exercicio;		
	}
		
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
		TreinoExercicioId other = (TreinoExercicioId) obj;
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
