package dao;

import java.sql.*;
import java.text.ParseException;

import model.Noticia;

public class DAONoticia {
	private static Connection conexao;
	
	public DAONoticia() {
		conexao = null;
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
	
	public boolean inserirNoticia(String titulo, String url, String urlToImage, char classificacao, Date dataDePublicacao) throws ParseException {
		boolean status = false;
	    
		String perfil = classificacao + "";
		try {  
			// Statement st = conexao.createStatement();
			// st.execute("INSERT INTO noticia (titulo, url, urlToImage, classificacao, dataDePublicacao) VALUES ("
			// 		+ "'"+titulo.replaceAll("'", "")+"',"
			// 		+ "'"+url+"',"
			// 		+ "'"+urlToImage+"',"
			// 		+ "'"+classificacao+"',"
			// 		+ "'"+dataDePublicacao+"')"); 

			PreparedStatement st = conexao.prepareStatement("INSERT INTO noticia (titulo, url, urlToImage, classificacao, dataDePublicacao) VALUES (?,?,?,?,?)");
			st.setString(1, titulo.replaceAll("'", ""));
			st.setString(2, url);
			st.setString(3, urlToImage);
			st.setString(4, perfil);
			st.setDate(5, dataDePublicacao); 
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public static boolean getExists(String titulo, char classificacao) {			
		Noticia noticia = new Noticia();		
		boolean resp = false;
		try {
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM noticia WHERE titulo LIKE ? AND classificacao LIKE ?");

			st.setString(1, titulo);
			st.setString(2, (classificacao + ""));
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				noticia = new Noticia(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"), 
						rs.getString("urlToImage"), rs.getString("classificacao").charAt(0), rs.getDate("dataDePublicacao"));
				if(noticia.getTitulo().compareTo(titulo) == 0) {
					resp = true;
				} 
			}
			st.close();	
		} catch (Exception e) {			
			System.err.println(e.getMessage());		
		}		
		return resp;	
	}
	
	public Noticia[] getNoticiasPerfil(char classificacao) {		
		Noticia[] noticia = null;
		
		try {
			// Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// ResultSet rs = st.executeQuery("SELECT * FROM noticia WHERE classificacao LIKE '" + classificacao + "' ORDER BY datadepublicacao DESC LIMIT 6 OFFSET 0");
			
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM noticia WHERE classificacao LIKE ? ORDER BY datadepublicacao DESC LIMIT 6 OFFSET 0",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			st.setString(1, (classificacao + ""));
			ResultSet rs = st.executeQuery();
			
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
	
	public Noticia[] getNoticiasPorData(char classificacao, Date data) {		
		Noticia[] noticia = null;
		
		try {
			// Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			// ResultSet rs = st.executeQuery("SELECT * FROM noticia WHERE noticia.classificacao LIKE '" + classificacao + "' AND noticia.datadepublicacao = '" + data.toString() + "'");
			
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM noticia WHERE noticia.classificacao LIKE ? AND noticia.datadepublicacao = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			st.setString(1, classificacao + "");
			st.setDate(2, data);
			ResultSet rs = st.executeQuery();
			
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
	
	public boolean excluirNoticia(int id) {
		boolean status = false;
		try {  
			// Statement st = conexao.createStatement();
			// st.executeUpdate("DELETE FROM noticia WHERE id = " + id);

			PreparedStatement st = conexao.prepareStatement("DELETE FROM noticia WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}