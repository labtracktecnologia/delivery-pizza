package com.labtrackensino.javaweb.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PIZZAS")
@SequenceGenerator(sequenceName = "SEQ_PIZZAS", name = "SEQ_PIZZAS", allocationSize = 1)
public class Pizza implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PIZZAS")
	@Column(name = "ID")
	private Long id;

	@Column(name = "CODIGO", length = 50)
	private String codigo;

	@Column(name = "QUANTIDADE_FATIAS")
	private Integer quantidaFatias;

	@Column(name = "QUANTIDADE_SABORES")
	private Integer quantidadeSabores;

	@Column(name = "BORDA")
	private Boolean borda;

	@Column(name = "PRECO")
	private BigDecimal preco;

	@Column(name = "DESCRICAO")
	private String descricao;


	public Pizza(String codigo, Integer quantidaFatias, Integer quantidadeSabores, Boolean borda, BigDecimal preco, String descricao) {
		this.codigo = codigo;
		this.quantidaFatias = quantidaFatias;
		this.quantidadeSabores = quantidadeSabores;
		this.borda = borda;
		this.preco = preco;
		this.descricao = descricao;
	}

	public Pizza() {
	}

	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public Integer getQuantidaFatias() {
		return quantidaFatias;
	}

	public Integer getQuantidadeSabores() {
		return quantidadeSabores;
	}

	public Boolean getBorda() {
		return borda;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setQuantidaFatias(Integer quantidaFatias) {
		this.quantidaFatias = quantidaFatias;
	}

	public void setQuantidadeSabores(Integer quantidadeSabores) {
		this.quantidadeSabores = quantidadeSabores;
	}

	public void setBorda(Boolean borda) {
		this.borda = borda;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
