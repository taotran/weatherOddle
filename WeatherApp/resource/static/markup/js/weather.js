function searchWeather() {
    var url = "/home/search";
    var input = $('#search-input').val();

    if (input != '') {
        url = url + "/" + input;
    }

    $("#resultsBlock").load(url);
}

function loadAll() {
    var url = "/home/showAll";

    $("#allWeatherBlock").load(url);
}

function deleteLog(id) {
    var url = "/home/delete/" + id;

    $("#allWeatherBlock").load(url);
}