const settings = {
	"async": true,
	"crossDomain": true,
	"url": "https://bloomberg-market-and-financial-news.p.rapidapi.com/news/list-by-region?id=europe-home-v3",
	"method": "GET",
	"headers": {
		"x-rapidapi-key": "a1ce6e37a4msh39b9fce0264984ap1499e8jsn520dadfb60ca",
		"x-rapidapi-host": "bloomberg-market-and-financial-news.p.rapidapi.com"
	}
};

function Montarnoticias (){

    $.ajax(settings).done(function (data) {
        
        console.log( data );
        
    })
    .done(function (data) {

        let codigoHTML = '';
        
        //Montar os cards
        for ( i = 4; i < 12; i++) {
        
            let titulo  = data.modules[1].stories[i].title;
            let imagem  = data.modules[1].stories[i].thumbnailImage;
            let sumario = data.modules[1].stories[i].summary;
            let site    = data.modules[1].stories[i].longURL;
        
            codigoHTML += `
            <div class="col-lg-4 col-xs-12 col-sm-6 col-md-4 pb-1 pt-0 pr-1">
            <div class="card border-0 rounded-0 text-white overflow zoom" width="100%" height="100%">
                <div class="position-relative">
                    <!--Thumbnail-->
                    <div class="ratio_right-cover-2 image-wrapper">
                        <a href="${site}" target="_blank">
                            <img width="100%" height="100%" class="img-fluid"
                                 src="${imagem}"
                                 alt="simple blog template bootstrap">
                        </a>
                    </div>
                    <div class="position-absolute p-2 p-lg-3 b-0 w-100 bg-shadow">

                        <!--Titulo-->
                        <a href="notices.html">
                            <h2 class="h5 text-white my-1 noticia-item">${titulo}</h2>
                        </a>
                    </div>
                </div>
            </div>
        </div>`;
        
            $("#cara_1").html(codigoHTML);
        }
    })
}

$(document).ready(function () {
    Montarnoticias();
});