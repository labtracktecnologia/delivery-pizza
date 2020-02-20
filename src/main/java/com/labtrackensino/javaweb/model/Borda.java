package com.labtrackensino.javaweb.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "BORDAS")
@SequenceGenerator(sequenceName = "SEQ_BORDAS", allocationSize = 1, name = "SEQ_BORDAS")
public class Borda implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BORDAS")
	private Long id;

	@NotNull(message = "O preço precisa ser informado")
	@Column(name = "PRECO")
	private BigDecimal preco;

	@Column(name = "DESCRICAO", length = 50, nullable = false)
	@NotNull(message = "{borda.descricao.vazia}")
	private String descricao;


	public Borda(BigDecimal preco, String descricao) {
		this.preco = preco;
		this.descricao = descricao;
	}

	public Borda() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
