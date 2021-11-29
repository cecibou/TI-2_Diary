package dao;

import java.sql.*;

import model.Noticia;
import model.Recomendacao;

public class DAORecomendacao {
	private Connection conexao = null;
	
	public DAORecomendacao() {
		this.conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";          
		String serverName = "diaryadm.postgres.database.azure.com";
		String mydatabase = "diary";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase; 
		String username = "admdiary@diaryadm";
		String password = "@Dmdiary";
		boolean status = false;
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}
		return status;
	}
	
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirRecomendacao(int conta_id, int noticia_id, String dataDeRecomendacao) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.execute("INSERT INTO recomendacao (conta_id, noticia_id, dataderecomendacao) VALUES ("
					+ conta_id + ","+
					+ noticia_id + ","
					+ "'"+dataDeRecomendacao+"')");  
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Recomendacao[] getPorData(String data) {			
		Recomendacao[] recomendacao = null;		
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
			ResultSet rs = st.executeQuery("SELECT * FROM recomendacao WHERE dataderecomendacao = '" + data + "' ORDER BY dataderecomendacao DESC");			 
			if(rs.next()){	 
				rs.last();
				recomendacao = new Recomendacao[rs.getRow()];
				rs.beforeFirst();
				for(int i = 0; rs.next(); i++) {
					recomendacao[i] = new Recomendacao(rs.getInt("conta_id"), rs.getInt("noticia_id"), rs.getDate("dataderecomendacao"));	
				}
			}	 
			st.close();		
		} catch (Exception e) {			
			System.err.println(e.getMessage());		
		}		
		return recomendacao;	
	}
	
	public Noticia[] getNoticiasPerfil(char classificacao) {		
		Noticia[] noticia = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM noticia WHERE classificacao LIKE '" + classificacao + "' ORDER BY datadepublicacao DESC LIMIT 10 OFFSET 0"); //	AND id > 120 LIMIT 10 OFFSET 0");	
			if(rs.next()){
				rs.last();
				noticia = new Noticia[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++) {
					noticia[i] = new Noticia(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"), 
					rs.getString("urlToImage"), rs.getString("classificacao").charAt(0), rs.getDate("dataDePublicacao"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return noticia;
	}
	
	public Noticia getNoticia(int id) {		
		Noticia noticia = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM noticia WHERE id = " + id);
			if(rs.next()){

				noticia = new Noticia(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"), 
					rs.getString("urlToImage"), rs.getString("classificacao").charAt(0), rs.getDate("dataDePublicacao"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return noticia;
	}
	
	public boolean getExists(int idNoticia, int idConta) {			
		Recomendacao recomendacao = new Recomendacao();		
		boolean resp = false;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
			ResultSet rs = st.executeQuery("SELECT * FROM recomendacao WHERE conta_id = " + idConta + " AND noticia_id = " + idNoticia);			 
			if(rs.next()){	 
				
				recomendacao = new Recomendacao(rs.getInt("conta_id"), rs.getInt("noticia_id"), rs.getDate("dataderecomendacao"));	
				if(recomendacao.getContaId() == idConta && recomendacao.getNoticiaId() == idNoticia) {
					resp = true;
				} else {
					resp = false;
				}
			}	 
			st.close();		
		} catch (Exception e) {			
			System.err.println(e.getMessage());		
		}		
		return resp;	
	}
	
	public Recomendacao[] getRecomendacoes(int id) {		
		Recomendacao[] recomendacao = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM recomendacao WHERE conta_id = " + id + " ORDER BY dataderecomendacao DESC LIMIT 18 OFFSET 0");		
			if(rs.next()){
				rs.last();
				recomendacao = new Recomendacao[rs.getRow()];
				rs.beforeFirst();

				for(int i = 0; rs.next(); i++) {
					recomendacao[i] = new Recomendacao(rs.getInt("conta_id"), rs.getInt("noticia_id"), rs.getDate("dataderecomendacao"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return recomendacao;
	}
	
	public boolean excluirRecomendacao(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM recomendacao WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}