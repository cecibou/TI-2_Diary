package dao;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
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
	/*
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
	
	public boolean inserirUsuario(Produto usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO produto (id, descricao, preco, quantidade, dataFabricacao, dataValidade) "
					       + "VALUES ("+usuario.getId()+ ", '" + usuario.getDescricao() + "', '"  
					       + usuario.getPreco() + "', '" + usuario.getQuant() +  "', '" + usuario.getDataFabricacao() + 
					       "', '" + usuario.getDataValidade() +"');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarUsuario(Produto usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE produto SET descricao = '" + usuario.getDescricao() + "', preco = '" + usuario.getPreco() 
					   + "', quantidade = '" + usuario.getQuant() + "', dataFabricacao = '" + usuario.getDataFabricacao() 
					   + "', dataValidade = '" + usuario.getDataValidade() + "'"
					   + " WHERE id = " + usuario.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirUsuario(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Produto[] getUsuarios() {
		Produto[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produto");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Produto[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getFloat("preco"), 
	                		rs.getInt("quantidade"), rs.getTimestamp("dataFabricacao").toLocalDateTime(), rs.getDate("dataValidade").toLocalDate());
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

	
	public Produto getUsuario(int codigo) {
		Produto[] usuarios = null;
		Produto usuario = new Produto();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM produto WHERE produto.id LIKE " + codigo);		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Produto[rs.getRow()];
	             rs.beforeFirst();

		         usuarios[0] = new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getFloat("preco"), 
		                		rs.getInt("quantidade"), rs.getTimestamp("dataFabricacao").toLocalDateTime(), rs.getDate("dataValidade").toLocalDate());
		         usuario = usuarios[0];
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}*/
}