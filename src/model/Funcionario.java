package model;

import java.io.Serializable;

public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String email;

	private int empresaId;

	private String nome;

	public Funcionario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmpresaId() {
		return this.empresaId;
	}

	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}