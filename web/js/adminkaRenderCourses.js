/**
 * Created by Sania_000 on 8/1/2014.
 */
if(!isLogin()){
    accessDeniedP();
}

document.getElementById("layoutAdminka").onload = function (){
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/user/courses",//"http://localhost:8080/rest/course/getAll",
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            var div = document.getElementById("coursesD");
            div.innerHTML = "";
            for (var i = 0; i < data.length; i++) {
                div.innerHTML +=                     '<div class="col-xs-12 col-sm-12 col-md-12 padding_5 course_medium">'+
                    '<div>'+
                    '<div class="news_cover_medium"><a href="javascript:openCoursePage(' + data[i].idCourse + ');">' +
                    '<img id = "courseMainImage'+ i +'" src="../img/image.jpg" alt="Course img" class="img-rounded"></a></div>'+

                    '<div class="news_content_medium">' +
                    '<a class="btn btn-default pull-right" onmouseover="" style="cursor: pointer;" onclick="deleteCourse(' + data[i].idCourse +')">' +
                    '<span class="glyphicon glyphicon-remove"></span></a>' +

                    '<a class="btn btn-default pull-right" href="Course_edit.html?' + data[i].idCourse + '">' +
                    ' <span class="glyphicon glyphicon-wrench"></span></a>' +

                    '<h3><a href="javascript:openCoursePage(' + data[i].idCourse + ');">' + data[i].name + '</a></h3>' +
                    '<div class="progress progress_fix">' +
                    '<div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">'+
                    '<span class="sr-only">45% Complete</span>'+
                    '</div>' +
                    '</div>' +
                    '<p>' +  data[i].description + '</p>'+
                    '</div>'+
                    '</div>'+
                    '</div>';
            }
            for (var i = 0; i < data.length; i++) {
                $("#courseMainImage"+i).attr('src','rest/course/getLogoImage/'+(data[i].idCourse));
            }
        }
    });
}

$.ajax({
    //data: str,
    type: "POST",
    url: "rest/userInfo",
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        document.getElementById("userNameShowing").innerHTML = data.login;
    }
});

function openCoursePage( indexOfCourse ){
    window.location = "/Adminka_couse.html?" + indexOfCourse;
}
