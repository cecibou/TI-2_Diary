package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;

import dao.DAORecomendacao;
import model.Noticia;
import model.NoticiaERecomendacao;
import model.Recomendacao;
import spark.Request;
import spark.Response;

public class RecomendacaoService {
	private DAORecomendacao recomendacaoDAO;
	
	public RecomendacaoService() {
		recomendacaoDAO = new DAORecomendacao();
		recomendacaoDAO.conectar();
	}
	
	public char getClassificacao (String perfil) {
		char classificacao = '_';
		
		if(perfil.charAt(0) == 'C') {
			classificacao = '1';
		} else if (perfil.charAt(0) == 'L') {
			classificacao = '2';
		} else if (perfil.charAt(0) == 'M') {
			classificacao = '3';
		}
		
		return classificacao;
	}
	
	public void getNewsESelecionar(int id, char classificacao) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		
		var recomendacao = recomendacaoDAO.getPorData(nowAsISO); 
		if(recomendacao == null) {
			int y = 0;
			Noticia [] noticia = recomendacaoDAO.getNoticiasPerfil(classificacao);
			for(int i = 0; i < 6 && y < 10; ) {
				if(! recomendacaoDAO.getExists(id, noticia[y].getId())) {
					recomendacaoDAO.inserirRecomendacao(id, noticia[y].getId(), nowAsISO);
					i++;
				}
				y++;
			}
		}
	}
	
	public Object getRecomendacoes(Request request, Response response) {
		var perfil = request.params("perfil");
		var idConta = request.params("id");
		int id = Integer.parseInt(idConta);
		char classificacao = getClassificacao(perfil);
		getNewsESelecionar(id, classificacao);
		
		Gson gson = new Gson();
		response.header("Content-Encoding", "UTF-8");
	    response.type("application/json");
	    response.status(200);
	    Recomendacao[] recomendacoes = recomendacaoDAO.getRecomendacoes(id);
	    NoticiaERecomendacao[] notERec = new NoticiaERecomendacao[recomendacoes.length];
	    Noticia noticia = new Noticia();
	    
	    for(int i = 0; i < recomendacoes.length; i++) {
	    	notERec[i] = new NoticiaERecomendacao();
	    	notERec[i].setDataDeRecomendacao(recomendacoes[i].getDataDeRecomendacao());
	    	noticia = recomendacaoDAO.getNoticia(recomendacoes[i].getNoticiaId());
	    	notERec[i].setTitulo(noticia.getTitulo());
	    	notERec[i].setURL(noticia.getURL());
	    	notERec[i].setURLToImage(noticia.getURLToImage());
	    	notERec[i].setDataDePublicacao(noticia.getDataDePublicacao());
	    }
	    
		return gson.toJson(notERec);
	}
}