console.log("HOHEY MATHIEU");
function refresh() {
    console.log("HOHEY MATHIEU");
    $.get('/profil', function (resultat) {
        console.log(resultat);
        var list = '';
        (resultat || []).forEach(function (user) {
            list = list
                + '<tr>'
                + '<td>' + user.id + '</td>'
                + '<td>' + user.name + '</td>'
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

$(document).ready(function () {
    refresh();
});
