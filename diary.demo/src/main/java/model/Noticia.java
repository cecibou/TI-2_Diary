package model;

import java.util.*;

public class Noticia {
	private Integer id;
	private String titulo;
	private String url;
	private String urlToImage;
	private char classificacao;
	private Date dataDePublicacao;	
	
	public Noticia() {
		this(0, null, null, null, '0', null);
	}
	
	public Noticia(Integer id, String titulo, String url, String urlToImage, char classificacao, Date dataDePublicacao) {
		setId(id);			
		setTitulo(titulo);
		setURL(url);
		setURLToImage(urlToImage);
		setClassificacao(classificacao);
		setDataDePublicacao(dataDePublicacao);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {	
			this.titulo = titulo;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
			this.url = url;
	}
	
	public String getURLToImage() {
		return urlToImage;
	}

	public void setURLToImage(String urlToImage) {
			this.urlToImage = urlToImage;
	}

	public char getClassificacao() {
		return classificacao;
	}
	
	public void setClassificacao(char classificacao) {
		this.classificacao = classificacao;
	}
	
	public Date getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(Date dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}

	public String toString() {
		return "ID Noticia: " + id + "   Titulo: " + titulo + "   URL: " + url + "   URLToImage: "
				 + urlToImage + "   Classificacao: " + classificacao  + "   Data de Publicacao: " 
				 + dataDePublicacao;
	}
}