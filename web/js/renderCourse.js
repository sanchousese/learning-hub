$.ajax({
    //data: str,
    type: "GET",
    url: "http://localhost:8080/rest/course/getAll",
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        var div = document.getElementById("coursesD");
        for (var i = 0; i < data.length; i++) {
            div.innerHTML +=
                '<div class="col-xs-12 col-sm-6 col-md-4  placeholder padding_5">' +
                '<div class="course_big">' +
                '<div class="news_cover"><img src="img/image.jpg" alt="Course img" class="img-rounded"></div>' +
                '<div class="news_content">' +
                '<h3>' + data[i].name+'</h3>' +
                '<p>' + data[i].description + '</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
    }
});