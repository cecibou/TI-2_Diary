package dao;

import java.sql.*;
import model.ContaDTO;

public class DAOConta {
	private Connection conexao = null;
	
	public DAOConta() {
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
	
	public boolean inserirConta(String email, String nome, String senha) {
		boolean status = false;
		try {  
			PreparedStatement st = conexao.prepareStatement(
				"INSERT INTO public.conta (email, nome, senha) VALUES (?, ?, ?)"
			);
			st.setString(1, email);
			st.setString(2, nome);
			st.setString(3, senha);
			st.execute();  
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean checkUser(String email, String senha) {
		boolean status = false;
		try {  
			PreparedStatement st = conexao.prepareStatement("SELECT count(*) FROM  public.conta WHERE email = ? AND senha = ?");
			st.setString(1, email);
			st.setString(2, senha);
			ResultSet rs= st.executeQuery(); 
			rs.next();
			int resultadoLogin = rs.getInt(1);
			status = resultadoLogin > 0;
			st.close();
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public ContaDTO getIdUsuario(String email) {			
		ContaDTO usuario = new ContaDTO();				
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
			ResultSet rs = st.executeQuery("SELECT * FROM conta WHERE email LIKE '" + email + "'");			 
			if(rs.next()){	 
				
				usuario = new ContaDTO(rs.getInt("id"), rs.getString("email"), rs.getString("nome"), 		 		
						rs.getString("senha"), rs.getString("estadoCivil"), rs.getString("personalidade"),
						rs.getDate("dataDeNascimento"));	
			}	 
			st.close();		
		} catch (Exception e) {			
			System.err.println(e.getMessage());		
		}		
		return usuario;	
	}
	
	public ContaDTO getPersonalidade(int id) {			
		ContaDTO usuario = new ContaDTO();				
		try {
			// Stateent st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
			PreparedStatement st = conexao.prepareStatement(
				"SELECT * FROM conta WHERE id = ?"
			);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
	
			if(rs.next()){	 
				usuario = new ContaDTO(rs.getInt("id"), rs.getString("email"), rs.getString("nome"), 		 		
						rs.getString("senha"), rs.getString("estadoCivil"), rs.getString("personalidade"),
						rs.getDate("dataDeNascimento"));	
			}	 
			st.close();		
		} catch (Exception e) {			
			System.err.println(e.getMessage());		
		}		

		return usuario;	
	}
	
	public boolean atualizarConta(String email, String estadoCivil, String personalidade, String dataDeNascimento) {
		boolean status = false;
		try {  
			PreparedStatement st = conexao.prepareStatement("UPDATE conta SET estadocivil = ?, datadenascimento = ?, personalidade = ? WHERE email LIKE ?");

			st.setString(1, estadoCivil);
			st.setString(2, dataDeNascimento);
			st.setString(3, personalidade);
			st.setString(4, email);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
