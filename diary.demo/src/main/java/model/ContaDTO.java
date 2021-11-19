package model;

public class ContaDTO {
  public ContaDTO(int pId,String pEmail,String pNome,String pSenha) {
	this.id = pId;
	this.email = pEmail;
	this.nome = pNome;
	this.senha = pSenha;
  }
  public int id;
  public String email;
  public String nome;
  public String senha; 
}

