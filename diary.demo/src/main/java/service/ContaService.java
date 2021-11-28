package service;

import com.google.gson.Gson;
import dao.DAOConta;
import model.ContaDTO;
import spark.Request;
import spark.Response;
import spark.Session;

public class ContaService {
	private DAOConta contaDAO;
	
	public ContaService() {
		contaDAO = new DAOConta();
		contaDAO.conectar();
	}
	
	public boolean save(String email, String nome, String senha) { 
		 return this.contaDAO.inserirConta(email, nome, senha);
		 
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
			boolean status = this.save(email,nome,senha);
			if(status) {
			response.redirect("login.html");
			}
			
		}
		catch(Exception e) {
			email= e.getMessage();
		}
		return gson.toJson(new ContaDTO(25,email,nome,senha));
	}

	public boolean Load(String email, String senha) { 
		 return this.contaDAO.checkUser(email, senha);
		 
	}
	
	public Object Login(Request request, Response response) {
		Gson gson = new Gson();
		
		response.status(200);
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    
		var email = request.raw().getParameter("email");
		var senha = request.raw().getParameter("senha");
		Session session = request.session();
		
		try {
			boolean status = this.Load(email,senha);
			if(status) {			
			//boolean userAuth = session.attribute("userAuth");
			session.attribute("userAuth", true);
				response.redirect("index.html?" + email);
			}else{
				session.attribute("userAuth", false);
				response.redirect("login.html");
			}
			
		}
		catch(Exception e) {
			email= e.getMessage();
		}
		return gson.toJson(new ContaDTO(25,email,senha));
	}
	
	public Object userAuth(Request request, Response response) {
		Gson gson = new Gson();
		
		response.status(200);
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    
		Session session = request.session();
		boolean status = false ;
		
		try {	
			//session.attribute("userAuth", true);
			 status = session.attribute("userAuth");
			
		}
		catch(Exception e) {
			status = false;
		}
		return gson.toJson("{userAuth:"+status+"}");
	}

	public Object getIDUsuario(Request request, Response response) {
		var email = request.params("email");
		Gson gson = new Gson();
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    String id = "";
	    
	    String emailConta = email;
	    ContaDTO conta = contaDAO.getIdUsuario(emailConta);
	    if(conta.getId() != 0) {
	    	id = Integer.toString(conta.getId());
	    } else {
	    	id = "0";
	    }
	    
		return gson.toJson(id);
	}
	
	public Object getPerfilUsuario(Request request, Response response) {
		var id = request.params("id");
		Gson gson = new Gson();
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    
	    int idUsuario = Integer.parseInt(id);
	    ContaDTO conta = contaDAO.getPersonalidade(idUsuario);
	    
		return gson.toJson(conta.getPersonalidade());
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
