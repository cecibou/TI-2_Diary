package service;

import com.google.gson.Gson;

import dao.DAOConta;
import model.ContaDTO;
import spark.Request;
import spark.Response;

public class ContaService {
	private DAOConta contaDAO;
	
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
	
	public String pegaAtributoText (String linha) {
		String[] aux = linha.split("\"");
		
		return aux[3];
	}
	
	public String pegaAtributoData (String linha) {
		String[] aux = linha.split("\"");
		String atributo = aux[6];
		String atributoClean = "";
		
		for(int i = 0; i < atributo.length(); i++) {
			if(atributo.charAt(i) != '\\') {
				atributoClean = atributoClean + (char) atributo.charAt(i);
			}
		}
		return atributoClean;
	}
	
	public Object inserirDadosChatbot(Request request, Response response) {
		var perfil = request.body();
		String dadosBot = perfil.intern();
		String email = "";
		String dataDeNascimento = "";
		String estadoCivil = "";
		String personalidade = "";
		
		String[] aux = dadosBot.split(",");
		
		for(int i = 0; i < aux.length; i++) {
			if(aux[i].contains("E-mail")) {
				email = pegaAtributoText(aux[i]);
			} else if(aux[i].contains("Data de Nascimento")) {
				dataDeNascimento = pegaAtributoData(aux[i]);
			} else if(aux[i].contains("Estado Civil")) {
				estadoCivil = pegaAtributoText(aux[i]);
			} else if(aux[i].contains("Personalidade")) {
				personalidade = pegaAtributoText(aux[i]);
			}
		}
		contaDAO.atualizarConta(email, estadoCivil, personalidade, dataDeNascimento);

	    return "ok";
	}
}