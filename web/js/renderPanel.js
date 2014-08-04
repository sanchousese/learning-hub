var query = window.location.search;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

$.ajax({
    //data: str,
    type: "GET",
    url: "rest/lessons?idCourse="+query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        setCourseNameInDiv();
        var div =  document.getElementById("listOfLessons");
        setMainForm(data[0].idLesson);
        for (var i = 0; i < data.length; i++) {
            div.innerHTML+= '<li class="list-group-item border-fix course-process-bg"><a onclick="setMainForm(' + data[i].idLesson + ')"><span class="glyphicon glyphicon-ok-sign"></span> '+ data[i].name +'</a></li>'
        }
    }
});

function setMainForm(data){
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/lessons/info/"+data,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {

            document.getElementById('lessonName').innerHTML = data.name;
            var panel =  document.getElementById("lessonDescription");
            panel.innerHTML = data.description;

            jwplayer('lessonVideo').setup({
                file: "/rest/course/getVideoCourse/" + query + "?n=1",//http://localhost:8080/rest/course/getVideoCourse/
                title: data.name,
                width: '100%',
                aspectratio: '16:9',
                autostart: false,
                type: "mp4"
            });
        }
    });
}

function setCourseNameInDiv(){
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/course/info/" + query,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            document.getElementById("courseNameDiv").innerHTML = data.name;
        }
    });
}