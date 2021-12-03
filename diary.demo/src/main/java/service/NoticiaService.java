package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	public static String apiKey = "&apiKey=ac412dde0ec34b16a87d620ff40f04f3";
	
	public NoticiaService() {
		noticiaDAO = new DAONoticia();
		noticiaDAO.conectar();
		numConservador = 0;
		numAgressivo = 0;
		numModerado = 0;
	}
	
	public void conexaoAPI(char classificacao, String url) throws Exception{
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(URI.create(url)).build();
	        
        try{
	        var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
	        var response = responseFuture.get();
	        //System.out.println(response.body());
	        NewsDTO obj = new Gson().fromJson(response.body(), NewsDTO.class);
	        if(classificacao == '1') {
		        for (ArticleDTO art : obj.articles) {
		        	if(numConservador < 10) {
						if( ! DAONoticia.getExists(art.title, '1')) {
							this.save(art.title, art.url, art.urlToImage, '1', art.publishedAt);
							numConservador++;
						}
		        	}
		        }
	        } else if (classificacao == '2') {
	        	for (ArticleDTO art : obj.articles) {
		        	if(numAgressivo < 10) {
		        		if( ! DAONoticia.getExists(art.title, '2')) {
			        		this.save(art.title, art.url, art.urlToImage, '2', art.publishedAt);
			        		numAgressivo++;
		        		}
		        	}
		        }
	        } else if(classificacao == '3') {
	        	for (ArticleDTO art : obj.articles) {
		        	if(numModerado < 10) {
		        		if( ! DAONoticia.getExists(art.title, '3')) {
			        		this.save(art.title, art.url, art.urlToImage, '3', art.publishedAt);
			        		numModerado++;
		        		}
		        	}
		        }
	        }
        }catch(Exception e){
	            e.printStackTrace();
	    }
	}
	
	public void getNewsAPI() throws Exception {
		String[] qConservador = {"selic", "cdi", "fixa", "finanças", "taxa", "guardar", "economia", "pagamento", "caixa", "PIB"};
		String[] qAgressivo = {"b3", "bovespa", "cripto", "investidor", "cotações", "ações", "bancos"};

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
		
		if(numModerado < 10) {
			this.conexaoAPI('3', (endPoint + apiKey));
		} 
	}
	
	public void save(String titulo, String url, String urlToImage, char classificacao, String dataDePublicacao) throws ParseException {
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataDePublicacao);
		java.sql.Date data = (java.sql.Date) new Date(date.getTime());
		this.noticiaDAO.inserirNoticia(titulo, url, urlToImage, classificacao, data);
	}
	
	public Object getNews(Request request, Response response) throws Exception {
		var perfil = request.params("perfil");
		Gson gson = new Gson();
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    long miliseconds = System.currentTimeMillis();
	    java.sql.Date date = (java.sql.Date) new Date(miliseconds);
		
		switch(perfil) {
			case "conservador":
	    	    response.status(200);
	    	    var noticiasAtuais = noticiaDAO.getNoticiasPorData('1', date);
	    	    if(noticiasAtuais == null) {
	    	    	this.getNewsAPI();
	    	    } 
				return gson.toJson(noticiaDAO.getNoticiasPerfil('1'));
			case "liberal":
				response.status(200);
				noticiasAtuais = noticiaDAO.getNoticiasPorData('2', date);
	    	    if(noticiasAtuais == null) {
	    	    	this.getNewsAPI();
	    	    } 
				return gson.toJson(noticiaDAO.getNoticiasPerfil('2'));
			case "moderado":
				response.status(200);
				noticiasAtuais = noticiaDAO.getNoticiasPorData('3', date);
	    	    if(noticiasAtuais == null) {
	    	    	this.getNewsAPI();
	    	    } 
				return gson.toJson(noticiaDAO.getNoticiasPerfil('3'));
			default:
				response.status(404);
				return gson.toJson("ERRO: Perfil inválido!");
		}
	}
}