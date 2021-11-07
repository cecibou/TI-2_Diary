package dao;

import java.sql.*;
import java.time.LocalDate;

import model.Noticia;

public class DAORecomendacao {
	private Connection conexao = null;
	
	public DAORecomendacao() {
		this.conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";          
		String serverName = "diary.postgres.database.azure.com";
		String mydatabase = "diary";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase; 
		String username = "adm@diary";
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
			st.execute("INSERT INTO recomendacao (conta_id, noticia_id, dataDeRecomendacao) VALUES ("
					+ "'"+conta_id+"',"
					+ "'"+noticia_id+"',"
					+ "'"+dataDeRecomendacao+"')");  
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
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