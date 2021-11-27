package app;

import service.ContaService;
import service.NoticiaService;
import spark.Filter;

import static spark.Spark.*;

public class Aplicacao {
	
	private static NoticiaService noticiaService = new NoticiaService();
	private static ContaService contaService = new ContaService();
	
	public static void main(String[] args) throws Exception{
		staticFiles.location("/");
		after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
        });

		//pegar noticias do banco de dados e mostrar no site
		get("/news/:perfil", (request, response) -> noticiaService.getNews(request, response));
		post("/contaSave", (request, response) -> contaService.saveAPI(request, response));
		post("/contaLogin", (request, response) -> contaService.Login(request, response));
		get("/userAuth", (request, response) -> contaService.userAuth(request, response));
		get("/contaLocalStorage/:email", (request, response) -> contaService.Login(request, response));
		put("/chatbot", (request, response) -> contaService.inserirDadosChatbot(request, response)); 
	
    }
}
