package app;

import service.ContaService;
import service.NoticiaService;
import service.RecomendacaoService;
import spark.Filter;

import static spark.Spark.*;

public class Aplicacao {
	
	private static NoticiaService noticiaService = new NoticiaService();
	private static ContaService contaService = new ContaService();
	private static RecomendacaoService recomendacaoService = new RecomendacaoService();
	
	public static void main(String[] args) throws Exception{
		
		String systemPort = System.getenv("PORT");
        int port = systemPort != null ? Integer.parseInt(systemPort) : 4567;
        port(port);
		

		staticFiles.location("/");
		after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
			response.header("Access-Control-Allow-Methods", "POST");
        });		

		get("/getPerfil/:id", (request, response) -> contaService.getPerfilUsuario(request, response));
		get("/userAuth", (request, response) -> contaService.userAuth(request, response));
		get("/contaLocalStorage/:email", (request, response) -> contaService.getIDUsuario(request, response));
		get("/recomendacao/:perfil/:id", (request, response) -> recomendacaoService.getRecomendacoes(request, response));
		post("/news/:perfil", (request, response) -> noticiaService.getNews(request, response));
		post("/contaSave", (request, response) -> contaService.saveAPI(request, response));
		post("/contaLogin", (request, response) -> contaService.Login(request, response));
		put("/chatbot", (request, response) -> contaService.inserirDadosChatbot(request, response)); 
    }
}
