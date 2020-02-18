package com.labtrackensino.javaweb.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "USUARIOS", indexes = {
		@Index(name = "idx_usuarios_email",
				columnList = "email", unique = true)
}
)
@SequenceGenerator(sequenceName = "SEQ_USUARIOS",
		name = "SEQ_USUARIOS", allocationSize = 1)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIOS")
	private Long id;

	@Length(message = "O Tamanho não pode ser maior que {max}", max = 100)
	@Column(name = "NOME" )
	private String nome;

	@Column(name = "SOBRENOME")
	private String sobrenome;

	@Column(name = "SENHA")
	private String senha;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (o == null || getClass() != o.getClass()){
			return false;
		}
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id) &&
				Objects.equals(nome, usuario.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, String senha, String telefone, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.senha = senha;
		this.telefone = telefone;
		this.email = email;
	}
}
