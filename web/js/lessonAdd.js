/**
 * Created by vasax32 on 04.08.14.
 */

function changeVisibilityButton() {
    //need to manage this code
//    if ($("#courseNameD").val() != "" && $("#courseDescD").val() != "" && $("#coursePriceD").val() != "" &&
//        document.getElementById("inputAgreement").checked == true
//        &&(category.spec != 0 && category.desc != 0
//            && category.subj != 0)) {
//        document.getElementById("addCourseButton").classList.remove('disabled') ;
//    }
//    else{
//        document.getElementById("addCourseButton").classList.add('disabled') ;
//    }
}

var query = window.location.search;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

function AddLessonInfo() {
    if(query == "") alert("Course identifier is wrong.");
    if ($("#lessonNameD").val() != "" && $("#lessonDescD").val() != "" && query != "") {
        var cour = {
            name: $("#lessonNameD").val(),
            description: $("#lessonDescD").val(),
            course: query
        };
        $.ajax({
            data: JSON.stringify(cour),
            type: "POST",
            url: "rest/lessons/create",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var lessonId = data;
                uploadLessonVideo(lessonId);
                //window.location.href = "index.html"; //redirect? need to uncomment
            }
        });
    }
}

function uploadLessonVideo(lessonId){
    var file_data = $("#uploadVideoFile").prop("files")[0];
    if (file_data != null) {
        var form_data = new FormData();
        var ajax = new XMLHttpRequest();
        ajax.upload.addEventListener("progress", progressHandler, false);
        ajax.open("POST", "rest/lessons/uploadVideo");
        form_data.append("file", file_data);
        form_data.append("lessonId", lessonId);
        ajax.addEventListener("load", function () {
            //window.location.href = "index.html";
            alert("loaded");
        }, false);
        ajax.addEventListener("error", function () {
            alert("Internal error");
        }, false);
        ajax.send(form_data);
//        window.location.href = "index.html";
    }
}

function progressHandler(event){
    var valeur = Math.round((event.loaded / event.total) * 100);
    //alert(valeur);
    $('.progress-bar').css('width', valeur+'%').attr('aria-valuenow', valeur);

}
