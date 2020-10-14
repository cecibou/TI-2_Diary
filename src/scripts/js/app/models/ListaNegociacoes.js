class ListaNegociacoes {
    
    constructor() {
        this._negociacoes = [];
    }
    
    adiciona(negociacao) {
        this._negociacoes.push(negociacao);
    }
    
    get negociacoes() {

         //return this._negociacoes;
        return [].concat(this._negociacoes);
    }

    esvazia(){

        this._negociacoes = []
        // ????
        //this._negociacoes = this_negociacoes.slice(2,-1);
        
        
    }
}