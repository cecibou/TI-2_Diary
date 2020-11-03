var quantidade_imagens = 0, x = 0;

fetch("https://bloomberg-market-and-financial-news.p.rapidapi.com/news/list-by-region?id=europe-home-v3", {
"method": "GET",
"headers": {
    "x-rapidapi-host": "bloomberg-market-and-financial-news.p.rapidapi.com",
    "x-rapidapi-key": "0aab032162msha5c2cb972a77e73p1581e7jsncbda6cf30b6d"
}
})

.then (  response => response.json() )
.then (  function( response )  {

    console.log ( response )

    for ( quantidade_imagens = 0;
          quantidade_imagens < x;
          quantidade_imagens ++ )
    {
        document.innerHTML = '<img scr = "'
                    +response.modules[0].stories[quantidade_imagens].thumbnailImage+'" >'
        document.innerHTML = 
                    '<p>'
                    +response.modules[0].stories[quantidade_imagens].title+
                    '<p>'
        document.innerHTML = 
                    '<p>'
                    +response.modules[0].stories[quantidade_imagens].abstract[0]+
                    '<p>'
    }
})                   
.then (  data     => data ) 
.catch(  err      => console.log(err) );