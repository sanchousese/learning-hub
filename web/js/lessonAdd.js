/**
 * Created by vasax32 on 04.08.14.
 */

function changeVisibilityButton() {
    //need to manage this code
    if ($("#lessonNameD").val() != "" && $("#lessonDescD").val() != "" && (ques.length != 0 ||
        ($("#queD").val() != "" && $("#ans1D").val() != ""  && $("#ans2D").val() != "" && $("#ans3D").val() != "" && $("#ans4D").val() != ""))) {
        document.getElementById("addLessonButton").classList.remove('disabled') ;
    }
    else{
        document.getElementById("addLessonButton").classList.add('disabled') ;
    }
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
                multipleQuesAdd(lessonId);
                window.location.href = "index.html"; //redirect? need to uncomment
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


//----------------Question-Answer-management-----------------------


function questionJ(q, a1, a2, a3, a4) {
    this.que = q;
    this.ans1 = a1;
    this.ans2 = a2;
    this.ans3 = a3;
    this.ans4 = a4;
    this.ans1b = false;
    this.ans2b = false;
    this.ans3b = false;
    this.ans4b = false;
}

var  ques = []; var cur = 0;

function nextQue() {
    if ($("#queD").val() != "") {
        saveQ();
        cur++;
        restoreQ();
    }
}

function prevQue(){
    if(cur > 0){
        saveQ();
        cur--;
        restoreQ();
    }

}

function saveQ(){
    ques[cur] = new questionJ($("#queD").val(), $("#ans1D").val(), $("#ans2D").val(), $("#ans3D").val(), $("#ans4D").val());
    ques[cur].ans1b = document.getElementById("ans1c").checked;
    ques[cur].ans2b = document.getElementById("ans2c").checked;
    ques[cur].ans3b = document.getElementById("ans3c").checked;
    ques[cur].ans4b = document.getElementById("ans4c").checked;
}

function restoreQ() {
    document.getElementById('qNum').innerHTML = "Вопрос " + (cur + 1);
    if (ques[cur] != null) {
        document.getElementById('queD').value = ques[cur].que;
        document.getElementById('ans1D').value = ques[cur].ans1;
        document.getElementById('ans2D').value = ques[cur].ans2;
        document.getElementById('ans3D').value = ques[cur].ans3;
        document.getElementById('ans4D').value = ques[cur].ans4;
        document.getElementById("ans1c").checked = ques[cur].ans1b;
        document.getElementById("ans2c").checked = ques[cur].ans2b;
        document.getElementById("ans3c").checked = ques[cur].ans3b;
        document.getElementById("ans4c").checked = ques[cur].ans4b;
        if (ques[cur].ans1b) {
            document.getElementById("ans1cl").classList.add("active");
        } else document.getElementById("ans1cl").classList.remove("active");
        if (ques[cur].ans2b) {
            document.getElementById("ans2cl").classList.add("active");
        } else document.getElementById("ans2cl").classList.remove("active");
        if (ques[cur].ans3b) {
            document.getElementById("ans3cl").classList.add("active");
        } else document.getElementById("ans3cl").classList.remove("active");
        if (ques[cur].ans4b) {
            document.getElementById("ans4cl").classList.add("active");
        } else document.getElementById("ans4cl").classList.remove("active");
    } else {
        document.getElementById('queD').value = '';
        document.getElementById('ans1D').value = '';
        document.getElementById('ans2D').value = '';
        document.getElementById('ans3D').value = '';
        document.getElementById('ans4D').value = '';
        document.getElementById("ans1c").checked = false;
        document.getElementById("ans1cl").classList.remove("active");
        document.getElementById("ans2c").checked = false;
        document.getElementById("ans2cl").classList.remove("active");
        document.getElementById("ans3c").checked = false;
        document.getElementById("ans3cl").classList.remove("active");
        document.getElementById("ans4c").checked = false;
        document.getElementById("ans4cl").classList.remove("active");
    }
}

function multipleQuesAdd(lesId){
    saveQ();
    for(var i = 0; i < ques.length; i++) {
        var sendD = ques[i];
        if(sendD.que != null || sendD != "") {
            var sendA = {
                lessonId: lesId,
                data: sendD
            }
            $.ajax({
                type: "POST",
                url: "rest/questions/create",
                data: JSON.stringify(sendA),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function () {

                    },
                    401: function () {
                        alert("Internal server error");
                    }
                }
            });
        }
    }
}



