package com.labtrackensino.javaweb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "PIZZAS_PEDIDOS")
@SequenceGenerator(sequenceName = "SEQ_PIZZAS_PEDIDOS", name = "SEQ_PIZZAS_PEDIDOS", allocationSize = 10)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class PizzaPedido extends Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "SEQ_PIZZAS_PEDIDOS")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_BORDAS", referencedColumnName = "ID")
	private Borda borda;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_PEDIDOS", referencedColumnName = "ID")
	@JsonBackReference
	private Pedido pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Borda getBorda() {
		return borda;
	}

	public void setBorda(Borda borda) {
		this.borda = borda;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


}
