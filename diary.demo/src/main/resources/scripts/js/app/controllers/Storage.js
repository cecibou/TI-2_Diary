function cadastraProduto(){
      
    var produtoData = document.getElementById("data");
    var produtoQuantidade = document.getElementById("quantiade");
    var produtoValor = document.getElementById("valor");

    var dados = JSON.parse(localStorage.getItem("dadosProduto"));

    if(dados == null){
       localStorage.setItem("dadosProduto", "[]");
       dados = [];
    }

    var auxRegistro = {
       nome: produtoData.value(),
       quantidade: produtoQuantidade.value(),
       valor: produtoValor.valor()
    }

    dados.push(auxRegistro);

    localStorage.setItem("dadosProduto", JSON.stringify(dados))
   

 }