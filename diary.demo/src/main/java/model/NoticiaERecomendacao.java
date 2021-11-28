package model;

import java.util.*;

public class NoticiaERecomendacao {
	private String titulo;
	private String url;
	private String urlToImage;
	private Date dataDePublicacao;	
	private Date dataDeRecomendacao;	
	
	public NoticiaERecomendacao() {
		this(null, null, null, null, null);
	}
	
	public NoticiaERecomendacao(String titulo, String url, String urlToImage, Date dataDePublicacao, Date dataDeRecomendacao) {		
		setTitulo(titulo);
		setURL(url);
		setURLToImage(urlToImage);
		setDataDePublicacao(dataDePublicacao);
		setDataDeRecomendacao(dataDeRecomendacao);
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
	
	public Date getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(Date dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}
	
	public Date getDataDeRecomendacao() {
		return dataDeRecomendacao;
	}

	public void setDataDeRecomendacao(Date dataDeRecomendacao) {
		this.dataDeRecomendacao = dataDeRecomendacao;
	}

	public String toString() {
		return "Titulo: " + titulo + "   URL: " + url + "   URLToImage: "
				 + urlToImage + "   Data de Publicacao: " + dataDePublicacao +
				 "   Data de Recomendacao: " + dataDeRecomendacao;
	}
}