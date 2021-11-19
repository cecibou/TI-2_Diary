package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

import com.google.gson.Gson;

import dao.DAOConta;
import model.ArticleDTO;
import model.ContaDTO;
import model.NewsDTO;
import spark.Request;
import spark.Response;

public class ContaService {
	private DAOConta contaDAO;
	public int numConservador;
	public int numAgressivo;
	public int numModerado;
	public static String endPoint = "https://newsapi.org/v2/top-headlines?country=br&category=business&q=";
	public static String apiKey = "&apiKey=63358a9c1d5b4fe88ac22d386a5a54ad";
	
	public ContaService() {
		contaDAO = new DAOConta();
		contaDAO.conectar();
	}
	
	public void save(String email, String nome, String senha) { 
		this.contaDAO.inserirConta(email, nome, senha);
	}
	public Object saveAPI(Request request, Response response) {
		Gson gson = new Gson();
		
		response.status(200);
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    
		var email = request.raw().getParameter("email");
		var nome = request.raw().getParameter("nome");
		var senha = request.raw().getParameter("senha");
		try {
			this.save(email,nome,senha);
		}
		catch(Exception e) {
			email= e.getMessage();
		}
		
		return gson.toJson(new ContaDTO(25,email,nome,senha));
		
	}
}