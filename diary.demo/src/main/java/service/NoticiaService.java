package com.ti2cc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import spark.Request;
import spark.Response;


public class ProdutoService {

	private DAO produtoDAO;

	public ProdutoService() {
		produtoDAO = new DAO();
		produtoDAO.conectar();
	} 

	public Object add(Request request, Response response) {
		Produto[] usuarios = produtoDAO.getUsuarios();
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("dataFabricacao"));
		LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));
		
    	int maiorCodigo = 0;
    	maiorCodigo = usuarios[0].getId();
    	for(int i = 1; i < usuarios.length; i++) {
    		if(usuarios[i].getId() > maiorCodigo)
    		{
    			maiorCodigo = usuarios[i].getId();
    		}
		}//fim for
		int id = maiorCodigo + 1;

		Produto produto = new Produto(id, descricao, preco, quantidade, dataFabricacao, dataValidade);

		produtoDAO.inserirUsuario(produto);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Produto produto = produtoDAO.getUsuario(id);
		
		if (produto != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<produto>\n" + 
            		"\t<id>" + produto.getId() + "</id>\n" +
            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + produto.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + produto.getDataValidade() + "</validade>\n" +
            		"</produto>\n";
        } else {
            response.status(404); // 404 Not found
            return "Produto " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
        Produto produto = produtoDAO.getUsuario(id);

        if (produto != null) {
        	produto.setDescricao(request.queryParams("descricao"));
        	produto.setPreco(Float.parseFloat(request.queryParams("preco")));
        	produto.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	produto.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	produto.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	produtoDAO.atualizarUsuario(produto);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Produto não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

            produtoDAO.excluirUsuario(id);

            response.status(200); // success
        	return id;
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<produtos type=\"array\">");
		for (Produto produto : produtoDAO.getUsuarios()) {
			returnValue.append("\n<produto>\n" + 
            		"\t<id>" + produto.getId() + "</id>\n" +
            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + produto.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + produto.getDataValidade() + "</validade>\n" +
            		"</produto>\n");
		}
		returnValue.append("</produtos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}