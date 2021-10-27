class NegociacaoController {
    
    constructor() {
        
        let $ = document.querySelector.bind(document);
        
        
        
        this._inputData = $('#data');
            this._inputQuantidade = $('#quantidade');
       this._inputValor = $('#valor');
        

        this._listaNegociacoes = new ListaNegociacoes();
        
        this._negociacoesView = new NegociacoesView($('#negociacoesView'));



        this._negociacoesView.update(this._listaNegociacoes);

      //  this._mensagemView = new MensagemView($('#mensagemView'));
     //   this._mensagemView.update(this.mensagem);
    }


  
    
    adiciona(event) {
        event.preventDefault();


        this._listaNegociacoes.adiciona(this._criaNegociacao());

         // adcionar a negociação a lista

        //console.log(negociacao);

        //console.log(this._inputData.value);
        //console.log(this._inputQuantidade.value);
        //console.log(this._inputValor.value);

        this._negociacoesView.update(this._listaNegociacoes);
       // this._mensagem.texto = 'Negociacao adicionada';
     //   this._mensagemView.update(this.mensagem);


        this._limpaFormulario();   
    }
    
        _criaNegociacao() {
            return new Negociacao(
                DateHelper.textoParaData(this._inputData.value),
                this._inputQuantidade.value,

              // let negociacao = new Negociacao(

            //this._inputData.value,
            //this._inputQuantidade.value,
          //  this._inputValor.value,
        //);

        //  console.log(this._ListaNegociacoes.negociacoes);
      //console.log(this._listaNegociacoes.negociacoes);


            this._inputValor.value);
               
             
        }

        apaga() {

            this._listaNegociacoes.esvazia();
            this._negociacoesView.update(this._listaNegociacoes);
        
          //  this._mensagem.texto = 'Negociações apagadas ';
         //   this._mensagemView.update(this._mensagem);
        }
    
    _limpaFormulario() {
     
            this._inputData.value = '';
            this._inputQuantidade.value = 1;
            this._inputValor.value = 0.0;
            

            this._inputData.focus();   
    }
}