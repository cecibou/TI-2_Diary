package model;

import java.util.Date;

public class ContaDTO {
	private int id;
	private String email;
	private String nome;
	private String senha; 
	private String estadoCivil;
	private String personalidade;
	private Date dataDeNascimento;
	
	public ContaDTO() {
		this(0, "", "", "", "", "", null);
	}
	
	public ContaDTO(int id, String email, String nome, String senha) {
		  this.id = id;
		  this.email = email;
		  this.nome = nome;
		  this.senha = senha;
	}
	public ContaDTO(int id, String email, String senha) {
		  this.id = id;
		  this.email = email;
		  this.senha = senha;
	}
	  
	public ContaDTO(int id, String email, String nome, String senha, String estadoCivil, 
					String personalidade, Date dataDeNascimento) {
		  this.id = id;
		  this.email = email;
		  this.nome = nome;
		  this.senha = senha;
		  this.estadoCivil = estadoCivil;
		  this.personalidade = personalidade;
		  this.dataDeNascimento = dataDeNascimento;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getPersonalidade() {
		return personalidade;
	}

	public void setPersonalidade(String personalidade) {
		this.personalidade = personalidade;
	}
	
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String toString() {
		return "ID Conta: " + id + "   E-mail: " + email + "   Nome: " + nome + "   Senha: "
				 + senha + "   Estado Civil: " + estadoCivil + "   Personalidade: " 
				 + personalidade  + "   Data de Nascimento: " + dataDeNascimento;
	}
}

