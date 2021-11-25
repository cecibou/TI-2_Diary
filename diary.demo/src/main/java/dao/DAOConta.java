package dao;

import java.sql.*;

public class DAOConta {
	private Connection conexao = null;
	
	public DAOConta() {
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
	
	public boolean inserirConta(String email, String nome, String senha) {
		boolean status = false;
		String sql= "INSERT INTO public.conta( "
				+ "email, nome, senha) "
				+ " VALUES ("
				+ "'"+ email + "',"
				+ "'"+ nome +"',"
				+ "'"+ senha +"')";
		try {  
			Statement st = conexao.createStatement();
			st.execute(sql);  
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarConta(int id, String estadoCivil, String personalidade, String dataDeNascimento) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE conta SET estadocivil = '" + estadoCivil + "', datadenascimento = '" +
					   dataDeNascimento + "', personalidade = '" + personalidade + "'"
					   + " WHERE id LIKE = " + id;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}