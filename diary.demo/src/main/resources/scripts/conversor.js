//Variaveis globais
const addCurrencyBtn = document.querySelector(".add-moedas-btn"); 
const addCurrencyList = document.querySelector(".add-moedas-list");
const currenciesList = document.querySelector(".moedas");

const initiallyDisplayerCurrencies = ["BRL","USD", "EUR", "GBP", "JPY",];
let baseCurrency;
let baseCurrencyAmount;

const currencies = [
    {
    name: "Real Brasileiro",
    abbreviation: "BRL",
    symbol: "\u0052\u0024",
    flagURL: "http://www.geonames.org/flags/l/br.gif",
    rate: 1
    },
    {
    name: "Dólar Americano",
    abbreviation: "USD",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/us.gif",
    rate: 0.1970
    },
    {
    name: "Euro",
    abbreviation: "EUR",
    symbol: "\u20AC",
    flagURL: "https://upload.wikimedia.org/wikipedia/commons/b/b7/Flag_of_Europe.svg",
    rate: 0.16272
    },
    {
    name: "Iene Japonês",
    abbreviation: "JPY",
    symbol: "\u00A5",
    flagURL: "http://www.geonames.org/flags/l/jp.gif",
    rate: 18.22
    },
    {
    name: "Libra Esterlina",
    abbreviation: "GBP",
    symbol: "\u00A3",
    flagURL: "http://www.geonames.org/flags/l/uk.gif",
    rate: 0.1492
    },
    {
    name: "Dólar Australiano",
    abbreviation: "AUD",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/au.gif",
    rate: 0.2478
    },
    {
    name: "Dólar Canadense",
    abbreviation: "CAD",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/ca.gif",
    rate: 0.2318
    },
    {
    name: "Franco Suíço",
    abbreviation: "CHF",
    symbol: "\u0043\u0048\u0046",
    flagURL: "http://www.geonames.org/flags/l/ch.gif",
    rate: 0.1597
    },
    {
    name: "Remimbi",
    abbreviation: "CNY",
    symbol: "\u00A5",
    flagURL: "http://www.geonames.org/flags/l/cn.gif",
    rate: 1.17
    },
    {
    name: "Coroa Sueca",
    abbreviation: "SEK",
    symbol: "\u006B\u0072",
    flagURL: "http://www.geonames.org/flags/l/se.gif",
    rate: 1.54
    },
    {
    name: "Dólar Neozelandês",
    abbreviation: "NZD",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/nz.gif",
    rate: 0.2629
    },
    {
    name: "Peso Mexicano",
    abbreviation: "MXN",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/mx.gif",
    rate: 3.69
    },
    {
    name: "Dólar Singapuriano",
    abbreviation: "SGD",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/sg.gif",
    rate: 0.24
    },
    {
    name: "Dólar de Hong Kong",
    abbreviation: "HKD",
    symbol: "\u0024",
    flagURL: "http://www.geonames.org/flags/l/hk.gif",
    rate: 1.35
    },
    {
    name: "Coroa Norueguesa",
    abbreviation: "NOK",
    symbol: "\u006B\u0072",
    flagURL: "http://www.geonames.org/flags/l/no.gif",
    rate: 1.66
    },
    {
    name: "Won Sul-Coreano",
    abbreviation: "KRW",
    symbol: "\u20A9",
    flagURL: "http://www.geonames.org/flags/l/kr.gif",
    rate: 197.92
    },
    {
    name: "Lira Turca",
    abbreviation: "TRY",
    symbol: "\u20BA",
    flagURL: "http://www.geonames.org/flags/l/tr.gif",
    rate: 1.45
    },
    {
    name: "Rublo Russo",
    abbreviation: "RUB",
    symbol: "\u20BD",
    flagURL: "http://www.geonames.org/flags/l/ru.gif",
    rate: 13.82
    },
    {
    name: "Rupia Indiana",
    abbreviation: "INR",
    symbol: "\u20B9",
    flagURL: "http://www.geonames.org/flags/l/in.gif",
    rate: 12.98
    },
    {
    name: "Rand Sul-Africano",
    abbreviation: "ZAR",
    symbol: "\u0052",
    flagURL: "http://www.geonames.org/flags/l/za.gif",
    rate: 2.83
    },
    {
    name: "Philippine Peso",
    abbreviation: "PHP",
    symbol: "\u20B1",
    flagURL: "http://www.geonames.org/flags/l/ph.gif",
    rate: 8.44
    },
    {
    name: "Czech Koruna",
    abbreviation: "CZK",
    symbol: "\u004B\u010D",
    flagURL: "http://www.geonames.org/flags/l/cz.gif",
    rate: 4.07
    },
    {
    name: "Indonesian Rupiah",
    abbreviation: "IDR",
    symbol: "\u0052\u0070",
    flagURL: "http://www.geonames.org/flags/l/id.gif",
    rate: 2540.61
    },
    {
    name: "Malaysian Ringgit",
    abbreviation: "MYR",
    symbol: "\u0052\u004D",
    flagURL: "http://www.geonames.org/flags/l/my.gif",
    rate: 0.72
    },
    {
    name: "Hungarian Forint",
    abbreviation: "HUF",
    symbol: "\u0046\u0074",
    flagURL: "http://www.geonames.org/flags/l/hu.gif",
    rate: 54.78
    },
    {
    name: "Icelandic Krona",
    abbreviation: "ISK",
    symbol: "\u006B\u0072",
    flagURL: "http://www.geonames.org/flags/l/is.gif",
    rate: 24.56
    },
    {
    name: "Croatian Kuna",
    abbreviation: "HRK",
    symbol: "\u006B\u006E",
    flagURL: "http://www.geonames.org/flags/l/hr.gif",
    rate: 1.13
    },
    {
    name: "Bulgarian Lev",
    abbreviation: "BGN",
    symbol: "\u043B\u0432",
    flagURL: "http://www.geonames.org/flags/l/bg.gif",
    rate: 0.2914
    },
    {
    name: "Romanian Leu",
    abbreviation: "RON",
    symbol: "\u006C\u0065\u0069",
    flagURL: "http://www.geonames.org/flags/l/ro.gif",
    rate: 0.7265
    },
    {
    name: "Danish Krone",
    abbreviation: "DKK",
    symbol: "\u006B\u0072",
    flagURL: "http://www.geonames.org/flags/l/dk.gif",
    rate: 1.11
    },
    {
    name: "Thai Baht",
    abbreviation: "THB",
    symbol: "\u0E3F",
    flagURL: "http://www.geonames.org/flags/l/th.gif",
    rate: 5.42
    },
    {
    name: "Polish Zloty",
    abbreviation: "PLN",
    symbol: "\u007A\u0142",
    flagURL: "http://www.geonames.org/flags/l/pl.gif",
    rate: 0.69
    },
    {
    name: "Israeli Shekel",
    abbreviation: "ILS",
    symbol: "\u20AA",
    flagURL: "http://www.geonames.org/flags/l/il.gif",
    rate: 0.59
    }
];

//Escutar evento

addCurrencyBtn.addEventListener("click", addCurrencyBtnClick);

function addCurrencyBtnClick(event) {
    addCurrencyBtn.classList.toggle("open");
}

addCurrencyList.addEventListener("click", addCurrencyListClick);

function addCurrencyListClick (event) {
    const clickedListItem = event.target.closest("li");
    if (!clickedListItem.classList.contains("disabled")) {
        const newCurrency = currencies.find(c => c.abbreviation === clickedListItem.getAttribute("data-currency"));
        if (newCurrency) newCurrenciesListItem(newCurrency);
    }
}

currenciesList.addEventListener("click", currenciesListClick);

function currenciesListClick (event) {
    if (event.target.classList.contains("close")) {
        const parentNode = event.target.parentNode;
        parentNode.remove();
        addCurrencyList.querySelector(`[data-currency=${parentNode.id}]`).classList.remove("disabled");
        if (parentNode.classList.contains("moeda-base")) {
            const newBaseCurrencyLI = currenciesList.querySelector(".moeda");
            if (newBaseCurrencyLI) {
                setNewBaseCurrency(newBaseCurrencyLI);
                baseCurrencyAmount = Number(newBaseCurrencyLI.querySelector(".input input").value);
            }
        }
    } 
}

function setNewBaseCurrency (newBaseCurrencyLI) {
    newBaseCurrencyLI.classList.add("moeda-base");
    baseCurrency = newBaseCurrencyLI.id;
    const baseCurrencyRate = currencies.find(currency => currency.abbreviation === baseCurrency).rate;
    currenciesList.querySelectorAll(".moeda").forEach(currencyLI => {
        const currencyRate = currencies.find(currency => currency.abbreviation === currencyLI.id).rate;
        const exchangeRate = currencyLI.id === baseCurrency ? 1 : (currencyRate/baseCurrencyRate).toFixed(3);
        currencyLI.querySelector(".base-moeda-rate").textContent = `1 ${baseCurrency} = ${exchangeRate} ${currencyLI.id}`;
    });
}

currenciesList.addEventListener("input", currenciesListInputChange);


function currenciesListInputChange (event) {
    const isNewBaseCurrency = event.target.closest("li").id !== baseCurrency;
    if (isNewBaseCurrency) {
        currenciesList.querySelector(`#${baseCurrency}`).classList.remove("moeda-base");
        setNewBaseCurrency (event.target.closest("li"));
    }
    const newBaseCurrencyAmount =isNaN(event.target.value) ? 0 : Number(event.target.value);
    if (baseCurrencyAmount !== newBaseCurrencyAmount || isNewBaseCurrency) {
        baseCurrencyAmount = newBaseCurrencyAmount;
        const baseCurrencyRate = currencies.find(currency => currency.abbreviation === baseCurrency).rate;
        currenciesList.querySelectorAll(".moeda").forEach(currencyLI => {
            if (currencyLI.id !== baseCurrency) {
                const currencyRate = currencies.find(currency => currency.abbreviation === currencyLI.id).rate;
            const exchangeRate = currencyLI.id === baseCurrency ? 1 : (currencyRate/baseCurrencyRate).toFixed(3);
            currencyLI.querySelector(".input input").value = exchangeRate*baseCurrencyAmount !== 0 ? (exchangeRate*baseCurrencyAmount).toFixed(3) : "";
            }
        });
    }
}

currenciesList.addEventListener("focusout", currenciesListFocusOut);

function currenciesListFocusOut (event) {
    const inputValue = event.target.value;
    if (isNaN(inputValue) || Number(inputValue) === 0) event.target.value = "";
    else event.target.value = Number(inputValue).toFixed(2);
}

currenciesList.addEventListener("keydown", currenciesListKeyDown);

function currenciesListKeyDown (event) {
    if (event.key === "Enter") event.target.blur();
}

//Function auxiliar

function populateAddCurrencyList () {
    for (let i = 0; i < currencies.length; i++) {
        addCurrencyList.insertAdjacentHTML(
            "beforeend",
            `<li data-currency=${currencies[i].abbreviation}>
                <img class="flag" src=${currencies[i].flagURL}><span>${currencies[i].abbreviation} - ${currencies[i].name}</span>
            </li>`
        );
    }
}

function populateCurrencyList () {
    for (let i = 0; i < initiallyDisplayerCurrencies.length; i++) {
        const currency = currencies.find(c => c.abbreviation === initiallyDisplayerCurrencies[i]);
        if (currency) newCurrenciesListItem(currency);
    }
}

function newCurrenciesListItem (currency) {
    if (currenciesList.childElementCount === 0) {
        baseCurrency = currency.abbreviation;
        baseCurrencyAmount = 0;
    }
    addCurrencyList.querySelector(`[data-currency=${currency.abbreviation}]`).classList.add("disabled");
    const baseCurrencyRate = currencies.find(c => c.abbreviation === baseCurrency).rate;
    const exchangeRate = currency.abbreviation === baseCurrency ? 1 : (currency.rate/baseCurrencyRate).toFixed(3);
    const inputValue = baseCurrencyAmount ? (baseCurrencyAmount*exchangeRate).toFixed(3) : "";

    currenciesList.insertAdjacentHTML(
        "beforeend",
        `<li class="moeda ${currency.abbreviation === baseCurrency ? "moeda-base" : ""}" id=${currency.abbreviation}>
            <img class="flag" src=${currency.flagURL}>
            <div class="info">
                <p class="input"><span class="moeda-symbol">${currency.symbol}</span><input placeholder="0.00" value=${inputValue}></p>
                <p class="moeda-name">${currency.abbreviation} - ${currency.name}</p>
                <p class="base-moeda-rate">1 ${baseCurrency} = ${exchangeRate} ${currency.abbreviation}</p>
            </div>
        <span class="close">&times;</span>
    </li>`
    );
}

/*
var saida = [];

fetch("https://bloomberg-market-and-financial-news.p.rapidapi.com/market/get-cross-currencies?id=aed%252Caud%252Cbrl%252Ccad%252Cchf%252Ccnh%252Ccny%252Ccop%252Cczk%252Cdkk%252Ceur%252Cgbp%252Chkd%252Chuf%252Cidr%252Cils%252Cinr%252Cjpy%252Ckrw%252Cmxn%252Cmyr%252Cnok%252Cnzd%252Cphp%252Cpln%252Crub%252Csek%252Csgd%252Cthb%252Ctry%252Ctwd%252Cusd%252Czar", {
	"method": "GET",
	"headers": {
		"x-rapidapi-host": "bloomberg-market-and-financial-news.p.rapidapi.com",
		"x-rapidapi-key": "840e6cb406msh2bc2c336eeae8e6p1b4caajsn66fc0674b978"
	}
})
.then(function (response) {
    return response.json();
})
.then(function (data) {
    saida = data.result;
    console.log(saida);
    currencies[1].rate = saida["usdbrl:cur"].last;
    console.log(currencies[1].rate);

    for() 

        cur =    {
            name: saida.catch....,
            abbreviation: "BRL",
            symbol: "\u0052\u0024",
            flagURL: "http://www.geonames.org/flags/l/br.gif",
            rate: 1
        };

        currencies.push(cur);

    atualizaTela ()
})
.catch(err => {
	console.log(err);
});
*/


populateAddCurrencyList();
populateCurrencyList();



