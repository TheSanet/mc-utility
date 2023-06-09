package org.jsanz.core.batch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parametro {
	
	@Id
	private Integer id;
	
	private String valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
