var query = window.location.search;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

var idLesson = 0;
var sizeOfCourse=0;
var testLesson = 1;
var idLesArr = []

$.ajax({
    //data: str,
    type: "GET",
    url: "rest/lessons?idCourse="+query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        setCourseNameInDiv();
        var div =  document.getElementById("listOfLessons");

        // trash
        var div2 = document.getElementById("");

        //


        setMainForm(data[idLesson].idLesson);
        sizeOfCourse = data.length;
        //setLastLID(data[data.length-1].idLesson);
        showAllCommentsLesson(data[0].idLesson);
        for (var i = 0; i < data.length; i++) {
            var style;
            if (checkLesson(data[i].idLesson))
                style = "glyphicon-ok-sign";
            else style = "glyphicon-exclamation-sign";
            div.innerHTML+= '<li class="list-group-item border-fix course-process-bg"><a onclick="setMainForm('
                + data[i].idLesson + ');' + 'showAllCommentsLesson(' + data[i].idLesson + ');' + '"><span class="glyphicon '+ style + '"></span> '+ data[i].name +'</a></li>'
            idLesArr[i] = data[i].idLesson;
        }
    }
});

function checkLesson(idL) {
    var rez = true;
    $.ajax({
        //data: str,
        async: false,
        type: "GET",
        url: "rest/lessons/checkUserLesson/" + idL,
        datatype: "json",
        contentType: "application/json",
        statusCode: {
            200: function () {
                //return true;
                rez =  true;
                //alert("lesson passed");
            },
            204: function () {
                //return false;
                rez = false;
                //alert("lesson not passed")
            }
        }
    });
    return rez;
}

function setMainForm(data){
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/lessons/lesson/"+data,
        datatype: "json",
        contentType: "application/json",
        success: function(dataL) {
            document.getElementById('lessonName').innerHTML = dataL.name;
            var panel =  document.getElementById("lessonDescription");
            panel.innerHTML = dataL.description;
            testLesson = dataL.idLesson;

            jwplayer('lessonVideo').setup({
                file: "/rest/course/getVideoCourse/" + query + "?n=1", //need to manage lesson video but course video
                title: dataL.name,
                width: '100%',
                aspectratio: '16:9',
                autostart: false,
                type: "mp4"
            });

            //setup to test button
            if(checkLesson(data)) {
                //lesson passed
                document.getElementById("goButton").classList.add('disabled') ;
                document.getElementById("goButton").innerHTML = "Пройдено";
            } else {
                $.ajax({
                    //data: str,
                    async: false,
                    type: "GET",
                    url: "rest/lessons/testExist/" + data,
                    datatype: "json",
                    contentType: "application/json",
                    statusCode: {
                        200: function () {
                            // test exist
                            document.getElementById("goButton").classList.remove('disabled') ;
                            document.getElementById("goButton").innerHTML = "Пройти тест модуля";
                        },
                        204: function () {
                            //test not exist
                            document.getElementById("goButton").classList.add('disabled') ;
                            document.getElementById("goButton").innerHTML = "Отсутствует";
                        }
                    }
                });
            }
        }
    });
}

function update() {
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/lessons?idCourse="+query,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            setMainForm(data[idLesson].idLesson);
            sizeOfCourse = data.length;
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

function goToTests() {
    if(!document.getElementById("goButton").classList.contains("disabled"))
        window.location = "/quiz.html?" + testLesson;
}

function goToNextLesson() {
    idLesson++;
    if(idLesson >= sizeOfCourse){
        idLesson = sizeOfCourse-1;
        return;
    }

    update();
}

function goToPrevLesson() {
    idLesson--;
    if(idLesson < 0){
        idLesson = 0;
        return;
    }

    update();
}