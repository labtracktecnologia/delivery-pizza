package com.labtrackensino.javaweb.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Item implements Serializable {

	@Column(name = "PRECO")
	private BigDecimal preco;

	@Column(name = "DESCRICAO")
	private String descricao;

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
