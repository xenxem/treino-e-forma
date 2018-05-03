package br.com.treinoeforma.model;

import java.io.Serializable;
import java.util.Calendar;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Treino implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)	
	private Calendar dataFim;
	
	private String descricao;
	private Integer curtidas;
	
		
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Calendar getDataFim() {
		return dataFim;
	}
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCurtidas() {
		return curtidas;
	}
	public void setCurtidas(Integer curtidas) {
		this.curtidas = curtidas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Treino other = (Treino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
	
}
