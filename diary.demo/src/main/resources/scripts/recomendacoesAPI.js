//Mostrar noticias do banco de dados
function MontarNoticias (){
    //pegar id no localStorage
    var id = localStorage.getItem("id");
    var perfil;
    //verificar se o id e' valido, ou seja se usuario esta logado
    if(! id || id < 0 ) {
        window.location.href = `${window.location.protocol}//${window.location.host}/login.html` 
        
        alert("Para ver notícias recomendadas é necessário fazer Login!");
        return; //este return vazio e para nao executar o codigo abaixo
    } else { 
        //pegar personalidade do usuario logado
        const settings1 = {
            "async": true,           //ser assincrono
            "crossDomain": true,     //pegar de outros dominios
            //"url": `${window.location.protocol}//${window.location.host}/getPerfil/${id}`,
            "url": `http://localhost:4567/getPerfil/${id}`,
            "method": "GET"
        };

        $.ajax(settings1).done(function (data) {
            perfil = data;

            //verificar se ha uma personalidade valida
            if (perfil != "liberal" && perfil != "conservador" && perfil != "moderado") {

                window.location.href = `${window.location.protocol}//${window.location.host}/bot.html`;
                alert("Para ver notícias recomendadas é necessário classificar seu perfil no Chatbot!");
                return; //este return vazio e para nao executar o codigo abaixo
            } else {
                //inserir noticias atuais da API no banco de dados
                const settings2 = {
                    "async": true,           
                    "crossDomain": true,     
                    //"url": `${window.location.protocol}//${window.location.host}/news/${perfil}`,
                    "url": `http://localhost:4567/news/${perfil}`,
                    "method": "POST"
                };
                $.ajax(settings2).done(function (data) {
                })

                //inserir na tabela recomendacao no banco de dados novas recomendacoes
                const settings3 = {
                    "async": true,           
                    "crossDomain": true,     
                    //"url": `${window.location.protocol}//${window.location.host}/recomendacao/${perfil}/${id}`,
                    "url": `http://localhost:4567/recomendacao/${perfil}/${id}`,
                    "method": "GET"
                };
                $.ajax(settings3).done(function (data) {
                    console.log(data);
                    let noticias = {};

                    data.forEach(element => {
                        if(noticias[element.dataDeRecomendacao]){
                            noticias[element.dataDeRecomendacao].push(element);
                        } else {
                            noticias[element.dataDeRecomendacao]= [element];
                        }
                    });

                    let elementosNoticias = []

                    for (var prop in noticias) {
                        elementosNoticias.push(`<div style="display: flex !important; width: 100%; text-align: justify; padding-bottom: 40px; padding-top: 40px"><h4 class="card-title">Data de Recomendação: ${prop}</h4></div>`);
                        noticias[prop].forEach(noticia=> {
                            elementosNoticias.push(
                                `<div class="card" style="width: 20rem; margin-bottom: 5%">
                                    <img src="${noticia.urlToImage === "null"?"https://www.fiscalti.com.br/wp-content/uploads/2021/06/planilhas-para-controle-financeiro-gratis-quantosobra.png":noticia.urlToImage}" class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">${noticia.titulo}</h5>
                                        <p>Data de Publicação: ${noticia.dataDePublicacao}</p>
                                        <a href="${noticia.url}" class="btn btn-primary" target="_blank">Ler notícia completa</a>
                                    </div>
                                </div>`)
                        })
                    }
                    $("#noticias").html(elementosNoticias);
                })
            }
        })
    }
}

//Quando todo o documento html estiver pronto
$(document).ready(function () {
    MontarNoticias();
});