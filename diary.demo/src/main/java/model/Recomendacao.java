package model;

import java.util.*;

public class Recomendacao {
	private int conta_id;
	private int noticia_id;
	private Date dataDeRecomendacao;	
	
	public Recomendacao() {
		this(0, 0, null);
	}
	
	public Recomendacao(int conta_id, int noticia_id, Date dataDeRecomendacao) {
		setContaId(conta_id);			
		setNoticiaId(noticia_id);
		setDataDeRecomendacao(dataDeRecomendacao);
	}		
	
	public int getContaId() {
		return conta_id;
	}

	public void setContaId(int conta_id) {
		this.conta_id = conta_id;
	}

	public int getNoticiaId() {
		return noticia_id;
	}

	public void setNoticiaId(int noticia_id) {
		this.noticia_id = noticia_id;
	}
	
	public Date getDataDeRecomendacao() {
		return dataDeRecomendacao;
	}

	public void setDataDeRecomendacao(Date dataDeRecomendacao) {
		this.dataDeRecomendacao = dataDeRecomendacao;
	}

	public String toString() {
		return "ID Conta: " + conta_id + "   ID Noticia " + noticia_id + 
				"   Data de Recomendacao: " + dataDeRecomendacao;
	}
}