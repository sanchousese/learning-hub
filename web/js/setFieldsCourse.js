var query = window.location.search;
// Skip the leading ?, which should always be there,
// but be careful anyway
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

$.ajax({
    //data: str,
    type: "GET",
    url: "http://localhost:8080/rest/course/info/" + query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        if(data.mainImagePath != null){
            document.getElementById("courseLogo").src = 'http://localhost:8080/rest/course/getLogoImage/'+ query;
        }

        document.getElementById("titleCourse").innerHTML = data.name;
        document.getElementById("priceCourse").innerHTML += data.price + " $";
        document.getElementById("descriptionCourse").innerHTML += data.description;

        if(data.mainVideoPath != null) {
            jwplayer('IntroVideo').setup({
                file: "http://localhost:8080/rest/course/getVideoCourse/" + query,
                title: 'Intro',
                width: '100%',
                aspectratio: '16:9',
                type: "mp4"
            });
        }else if(data.mainImagePath != null){
            document.getElementById("IntroImage").src = 'http://localhost:8080/rest/course/getLogoImage/'+ query;
        }
//        document.getElementById("IntroVideo").innerHTML = ;

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
        url: "http://localhost:8080/rest/course/info/" + query + "/numberOfPeople",
        datatype: String,
        contentType: "text/plain",
        success: function(data) {
            document.getElementById("numberUsersCourse").innerHTML = data + "\tStudents";
        }
    });

}