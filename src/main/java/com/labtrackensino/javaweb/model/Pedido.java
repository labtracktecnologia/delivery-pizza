package com.labtrackensino.javaweb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEDIDOS")
@SequenceGenerator(sequenceName = "SEQ_PEDIDOS", name = "SEQ_PEDIDOS", allocationSize = 1)
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDOS")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "I_USUARIOS", referencedColumnName = "ID")
	private Usuario usuario;

	@Column(name = "VALOR_TOTAL")
	private BigDecimal valorTotal;

	@OneToMany(mappedBy = "pedido",
			targetEntity = PizzaPedido.class,
			cascade = CascadeType.ALL)
	private List<PizzaPedido> pizzas = new ArrayList<>();

	public void setPizzas(List<PizzaPedido> pizzas) {
		this.pizzas.clear();
		this.pizzas.forEach(a -> a.setPedido(this));
		this.pizzas = pizzas;
	}

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public List<PizzaPedido> getPizzas() {
		return pizzas;
	}
}
