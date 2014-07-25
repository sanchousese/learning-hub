var query = window.location.search;
// Skip the leading ?, which should always be there,
// but be careful anyway
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

$.ajax({
    //data: str,
    type: "GET",
    url: "http://localhost:8080/rest/course/" + query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        document.getElementById("titleCourse").innerHTML = data.name;
        document.getElementById("priceCourse").innerHTML += data.price + " $";
        document.getElementById("descriptionCourse").innerHTML += data.description;

        for(i = 0; i < data.rate; i++)
            document.getElementById("ratingCourse").innerHTML += '<span class="glyphicon glyphicon-star"></span>';
        for(j = 0; j < 5 - data.rate; j++)
            document.getElementById("ratingCourse").innerHTML += '<span class="glyphicon glyphicon-star-empty"></span>';
        getNumberOfStudent();
    }
});


function getNumberOfStudent() {
    $.ajax({
        //data: str,
        type: "GET",
        url: "http://localhost:8080/rest/course/" + query + "/numberOfPeople",
        datatype: String,
        contentType: "text/plain",
        success: function(data) {
            document.getElementById("numberUsersCourse").innerHTML = data + "\tStudents";
        }
    });

}