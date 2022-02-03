$(document).ready(function () {
    $('#cubeNamed').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/releases/suggestions?", { query: request.term }, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );
})
