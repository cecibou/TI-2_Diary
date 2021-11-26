//Mostrar noticias do banco de dados
function MontarNoticias (){
    // var usuario = localStorage.getItem("diary.user");
    // if(! usuario) {
    //     window.location.href = "http://127.0.0.1:5501/diary.demo/src/main/resources/index.html";
    //     return; //este return vazio e para nao executar o codigo abaixo
    // } else if ( ! usuario.personalidade) {
    //     window.location.href = "http://127.0.0.1:5501/diary.demo/src/main/resources/investments.html";
    //     return; //este return vazio e para nao executar o codigo abaixo
    // }

    //const perfil = usuario.personalidade;

    const settings = {
        "async": true,           //ser assincrono
        "crossDomain": true,     //pegar de outros dominios
        "url": `http://localhost:4567/news/conservador`,  //${perfil}
        "method": "GET"
    };
    
    $.ajax(settings).done(function (data) {
        
        console.log( data );
        const elementosNoticias = data.map(noticia=>(
            `<div class="card" style="width: 20rem; margin-bottom: 5%">
                <img src="${noticia.urlToImage === "null"?"https://www.fiscalti.com.br/wp-content/uploads/2021/06/planilhas-para-controle-financeiro-gratis-quantosobra.png":noticia.urlToImage}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${noticia.titulo}</h5>
                    <p>Data de Publicação: ${noticia.dataDePublicacao}</p>
                    <a href="${noticia.url}" class="btn btn-primary" target="_blank">Ler notícia completa</a>
                </div>
            </div>`
        ));
        $("#noticias").html(elementosNoticias);
    })
}

//Quando todo o documento html estiver pronto
$(document).ready(function () {
    MontarNoticias();
});