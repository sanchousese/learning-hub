$.ajax({
    //data: str,
    type: "GET",
    url: "rest/course/getAll",//"http://localhost:8080/rest/course/getAll",
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        var div = document.getElementById("coursesD");
        for (var i = 0; i < data.length; i++) {
            div.innerHTML +=
                '<div class="col-xs-12 col-sm-6 col-md-4  placeholder padding_5" href="/Course.html">' +
                '<div class="course_big">' +
                '<div class="news_cover"><a href="javascript:openCourse(' + data[i].idCourse + ');">' +
                    '<img id = "courseMainImage'+ i +'" src="../img/image.jpg" alt="Course img" class="img-rounded"></a></div>' +
                '<div class="news_content">' +
                '<h3><a href="javascript:openCourse(' + data[i].idCourse + ');">' + data[i].name + '</a></h3>' +
                '<p>' + data[i].description + '</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        for (var i = 0; i < data.length; i++) {
            $("#courseMainImage"+i).attr('src','rest/course/getLogoImage/'+(data[i].idCourse));
        }
    }
});

function openCourse( indexOfCourse ){
    window.location = "/Course.html?" + indexOfCourse;
}
