package com.labtrackensino.javaweb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "BORDAS", indexes = {
        @Index(name = "idx_bordas_descricao", columnList = "descricao")
})
@SequenceGenerator(sequenceName = "SEQ_BORDAS", name = "SEQ_BORDAS", allocationSize = 1)
public class Borda  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BORDAS")
    private Long id;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Borda(BigDecimal preco, String descricao) {
        this.preco = preco;
        this.descricao = descricao;
    }

    public Borda() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }
}
