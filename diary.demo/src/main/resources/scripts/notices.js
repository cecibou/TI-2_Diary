//Comentários
function leComentario() {
  let strComentario = localStorage.getItem("db");
  let objComentario = {};

  if (strComentario) {
    objComentario = JSON.parse(strComentario);
  } else {
    objComentario = {
      comentarios: [{ data: "30/10/2020", texto: "Excelente informação!" }],
    };
  }

  return objComentario;
}

function salvaComentario(comentario) {
  localStorage.setItem("db", JSON.stringify(comentario));
}

function incluirComentario() {
  //Ler os comentarios do localStorage
  let objComentario = leComentario();

  //Incluir um novo comentario
  let strComentario = document.getElementById("comentario").value;
  if (strComentario !== "") {
    let novoComentario = { data: dataAtual(), texto: strComentario };
    objComentario.comentarios.push(novoComentario);

    //Salvar os comentarios no localStorage novamente
    salvaComentario(objComentario);

    //Atualiza os dados da tela
    imprimeComentario();
    document.getElementById("comentario").value = "";
  }
}

function naoHaComentario() {
  let objComentario = leComentario();
}

function imprimeComentario() {
  let tela = document.getElementById("tela");
  let strHtml = "";
  let objComentario = leComentario();

  for (i = 0; i < objComentario.comentarios.length; i++) {
    strHtml =
      strHtml +
      `<p>${objComentario.comentarios[i].data} - ${objComentario.comentarios[i].texto}</p>`;
  }

  tela.innerHTML = strHtml;
}

//Configura botão Enviar
document.getElementById("enviar").addEventListener("click", incluirComentario);

function dataAtual() {
  let datas = new Date();
  let dia = datas.getDate();
  let mes = datas.getMonth();
  let ano = datas.getFullYear();
  let strdata = dia + "/" + (mes + 1) + "/" + ano;

  return strdata;
}

onload = () => {
  //Atualiza os dados da tela
  imprimeComentario();
};
