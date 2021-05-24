function refresh() {
    $.get('/client', function (resultat) {
        console.log(resultat);
        var list = '';
        (resultat || []).forEach(function (fruit) {
            list = list
                + '<tr>'
                + '<td>' + fruit.id + '</td>'
                + '<td>' + fruit.name + '</td>'
                + '<td><a href="#" onclick="deleteFruit(' + fruit.id + ')">Delete</a></td>'
                + '</tr>'
        });
        if (list.length > 0) {
            list = ''
                + '<table><thead><th>Id</th><th>Name</th><th></th></thead>'
                + list
                + '</table>';
        } else {
            list = "No resultat in database"
        }
        $('#all-resultat').html(list);
    });
}