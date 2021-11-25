package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import com.google.gson.Gson;

import dao.DAONoticia;
import model.ArticleDTO;
import model.NewsDTO;
import spark.Request;
import spark.Response;

public class NoticiaService {
	private DAONoticia noticiaDAO;
	public int numConservador;
	public int numAgressivo;
	public int numModerado;
	public static String endPoint = "https://newsapi.org/v2/top-headlines?country=br&category=business&q=";
	public static String apiKey = "&apiKey=63358a9c1d5b4fe88ac22d386a5a54ad";
	
	public NoticiaService() {
		noticiaDAO = new DAONoticia();
		noticiaDAO.conectar();
		numConservador = 0;
		numAgressivo = 0;
		numModerado = 0;
	}
	
	public void conexaoAPI(char classificacao, String url) {
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(url)).build();
	        
        try{
	        var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
	        var response = responseFuture.get();
	        System.out.println(response.body());
	        NewsDTO obj = new Gson().fromJson(response.body(), NewsDTO.class);
	        if(classificacao == '1') {
		        for (ArticleDTO art : obj.articles) {
		        	if(numConservador < 10) {
		        		this.save(art.title, art.url, art.urlToImage, '1', art.publishedAt);
		        		numConservador++;
		        	}
		        }
	        } else if (classificacao == '2') {
	        	for (ArticleDTO art : obj.articles) {
		        	if(numAgressivo < 10) {
		        		this.save(art.title, art.url, art.urlToImage, '2', art.publishedAt);
		        		numAgressivo++;
		        	}
		        }
	        } else if(classificacao == '3') {
	        	for (ArticleDTO art : obj.articles) {
		        	if(numModerado < 10) {
		        		this.save(art.title, art.url, art.urlToImage, '3', art.publishedAt);
		        		numModerado++;
		        	}
		        }
	        }
        }catch(Exception e){
	            e.printStackTrace();
	    }
	}
	
	public void getNewsAPI() {
		String[] qConservador = {"selic", "cdi", "fixa", "finanças", "taxa", "guardar", "economia"};
		String[] qAgressivo = {"b3", "bovespa", "cripto", "investidor", "cotações", "ações"};

		for(int i = 0; i < qConservador.length; i++) {
			if(numConservador < 10) {
				this.conexaoAPI('1', (endPoint + qConservador[i] + apiKey));
			} else {
				i = qConservador.length;
			}
		}
		
		for(int i = 0; i < qAgressivo.length; i++) {
			if(numConservador < 10) {
				this.conexaoAPI('2', (endPoint + qAgressivo[i] + apiKey));
			} else {
				i = qAgressivo.length;
			}
		}
		
		for(int i = 0; i < qAgressivo.length; i++) {
			if(numModerado < 5) {
				this.conexaoAPI('3', (endPoint + qAgressivo[i] + apiKey));
			} else {
				i = qAgressivo.length;
			}
		}
		
		for(int i = 0; i < qConservador.length; i++) {
			if(numModerado < 10) {
				this.conexaoAPI('3', (endPoint + qConservador[i] + apiKey));
			} else {
				i = qConservador.length;
			}
		}
	}
	
	public void save(String titulo, String url, String urlToImage, char classificacao, String dataDePublicacao) {
		this.noticiaDAO.inserirNoticia(titulo, url, urlToImage, classificacao, dataDePublicacao);
	}
	
	public Object getNews(Request request, Response response) {
		var perfil = request.params("perfil");
		Gson gson = new Gson();
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
		
		switch(perfil) {
			case "conservador":
	    	    response.status(200);
	    	    var noticiasAtuais = noticiaDAO.getNoticiasPorData('1', LocalDate.now());
	    	    if(noticiasAtuais == null) {
	    	    	this.getNewsAPI();
	    	    } 
				return gson.toJson(noticiaDAO.getNoticiasPerfil('1'));
			case "liberal":
				response.status(200);
				noticiasAtuais = noticiaDAO.getNoticiasPorData('2', LocalDate.now());
	    	    if(noticiasAtuais == null) {
	    	    	this.getNewsAPI();
	    	    } 
				return gson.toJson(noticiaDAO.getNoticiasPerfil('2'));
			case "moderado":
				response.status(200);
				noticiasAtuais = noticiaDAO.getNoticiasPorData('3', LocalDate.now());
	    	    if(noticiasAtuais == null) {
	    	    	this.getNewsAPI();
	    	    } 
				return gson.toJson(noticiaDAO.getNoticiasPerfil('3'));
			default:
				response.status(404);
				return gson.toJson("ERRO: Perfil inválido!");
		}
	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

		noticiaDAO.excluirNoticia(id);

		response.status(200); // success
		return id;
	}//fim remove()
}