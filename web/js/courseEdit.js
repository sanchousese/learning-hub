if(!isLogin()){
    accessDeniedP();
    throw new Error();
}

var query = window.location.search;
if(query == "") document.body.innerHTML = "<H1>Course identifier is wrong</H1>"
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}


//var RATE;
//debugger;
var ID_COURSE = query;
//debugger;

function CCategory(){
    var spec = 0
    var desc = 0
    var subj = 0
    this.getSpecialty = function(){
        $.ajax({
            //data: str,
            type: "GET",
            url: "rest/course/getSpecialty",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("collapseOne");
                div.innerHTML = "";
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5" ' +
                        'id="spID' + data[i].idSpecialty +'" onclick="category.update(0, '+ data[i].idSpecialty +')">' +
                        '<input type="radio" name="options" id="option1" > '+ data[i].name+ '</label>';
                }
                category.desc = 0;
                //debugger;
            },
            statusCode: {
                410: function() {
                    alert("Internal error")
                }
            }
        });
    }
    this.getDiscipline = function(){
        //debugger;
        $.ajax({
            //data: category.spec,
            type: "GET",
            url: "rest/search/filter/disciplines?idSpecialty=" + category.spec,
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("DisciplineD");
                div.innerHTML = "";
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5"' +
                        'id="diID' + data[i].idDiscipline + '" onclick="category.update(1, '+ data[i].idDiscipline +')">' +
                        '<input type="radio" name="options" id="option1" > '+ data[i].name+ '</label>';
                }
                category.subj = 0;
            },
            statusCode: {
                404: function() {
                    var div = document.getElementById("DisciplineD");
                    div.innerHTML = "";
                }
            }
        });
    }
    this.getSubject = function(){
        $.ajax({
            //data: str,
            type: "GET",
            url: "rest/search/filter/subjects?idDiscipline=" + category.desc,
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("collapseThree");
                div.innerHTML = "";
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5" ' +
                        'id="suID' + (i + 1) + '" onclick="category.update(2, '+ data[i].idSubject +')">' +
                        '<input type="radio" name="options" id="option1" > '+ data[i].name+ '</label>';
                }
            },
            statusCode: {
                404: function() {
                    var div = document.getElementById("collapseThree");
                    div.innerHTML = "";
                }
            }
        });
    }
    this.update = function(type, index){
        if(type == 0){
            //alert("update " + index);
            category.spec = index;
            $("#discipline").removeAttr("disabled");
            $("#discipline").click();
            category.desc = 0;
            category.getDiscipline();
            category.getSubject();
        }
        if(type == 1){
            //alert("update 1" + index);
            category.desc = index;
            //$("#subjDHide").click();
            $("#subject").removeAttr("disabled");
            $("#subject").click();
            category.subj = 0;
            category.getSubject();
        }
        if(type == 2){
            //alert("update " + index);
            category.subj = index;
        }

        changeVisibilityButton();
    }
}


var category = new CCategory();
//category.getSpecialty();
//category.getDiscipline();
//category.getSubject();

function changeVisibilityButton() {
    if ($("#courseNameD").val() != "" && $("#courseDescD").val() != "" && $("#coursePriceD").val() != "" &&
        document.getElementById("inputAgreement").checked == true
        &&(category.spec != 0 && category.desc != 0
            && category.subj != 0)) {
        document.getElementById("addCourseButton").classList.remove('disabled') ;
    }
    else{
        document.getElementById("addCourseButton").classList.add('disabled') ;
    }
}


// TODO: MUST BE PUT
function AddCourseInfo() {
    if ($("#courseNameD").val() != "" && $("#courseDescD").val() != "" && $("#coursePriceD").val() != "" &&
        document.getElementById("inputAgreement").checked == true
        &&(category.spec != 0 && category.desc != 0
            && category.subj != 0)) {
        var cour = {
            name: $("#courseNameD").val(),
            description: $("#courseDescD").val(),
            price : $("#coursePriceD").val(),
            subject: category.subj,
            idCourse: ID_COURSE
  //          rate: RATE

        };
        $.ajax({
            data: JSON.stringify(cour),
            type: "PUT",
            url: "rest/course/put",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var courseId = data;
                uploadCourseLogo(courseId);
                uploadCourseIntro(courseId);
                window.location.href = "Adminka.html";
                //window.location.href = "index.html"; //redirect? need to uncomment
            }
        });
    }
}

function uploadCourseLogo(courseId) {
    var file_data = $("#uploadImageFile").prop("files")[0];
    if (file_data != null) {
        //alert("image found");
        var form_data = new FormData();
        var ajax = new XMLHttpRequest();
        //ajax.upload.addEventListener("progress", progressHandler, false);
        ajax.open("POST", "rest/course/uploadMainLogo");
        form_data.append("file", file_data);
        form_data.append("courseId", courseId);
        ajax.addEventListener("success", function () {
            //alert("upload ok.")
        }, false);
        ajax.addEventListener("error", function () {
            alert("Internal error");
        }, false);
        ajax.send(form_data);
    }
}

function uploadCourseIntro(courseId){
    var file_data = $("#uploadVideoFile").prop("files")[0];
    if (file_data != null) {
        var form_data = new FormData();
        var ajax = new XMLHttpRequest();
        ajax.upload.addEventListener("progress", progressHandler, false);
        ajax.open("POST", "rest/course/uploadMainIntro");
        form_data.append("file", file_data);
        form_data.append("courseId", courseId);
        ajax.addEventListener("load", function () {
            //window.location.href = "index.html";
        }, false);
        ajax.addEventListener("error", function () {
            alert("Internal error");
        }, false);
        ajax.send(form_data);
    }
}


//======================================FILL COURSE GAPS=====================================

function fillCourse(idCourse) {


// GETTING THE COURSE
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/course/info/" + idCourse,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            // fill fields
            document.getElementById("courseNameD").value = data.name;
            document.getElementById("courseDescD").value = data.description;
            document.getElementById("coursePriceD").value = data.price;
            //RATE = data.rate;

//            setItFree();
            if (data.price == 0 || data.price == null) {
                document.getElementById("FreeCourse").checked =  true;
            }


            category.spec = data.subject.discipline.specialty.idSpecialty;
            category.desc = data.subject.discipline.idDiscipline;
            category.subj = data.subject.idSubject;
            $.ajax({
                //data: str,
                type: "GET",
                url: "rest/course/getSpecialty",
                datatype: "json",
                contentType: "application/json",
                success: function(dataS) {
                    var div = document.getElementById("collapseOne");
                    div.innerHTML = "";
                    for(var i = 0; i < dataS.length; i++){
                        if(dataS[i].idSpecialty != data.subject.discipline.specialty.idSpecialty) {
                            div.innerHTML +=
                                '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5" ' +
                                'id="spID' + dataS[i].idSpecialty + '" onclick="category.update(0, ' + dataS[i].idSpecialty + ')">' +
                                '<input type="radio" name="options" id="option1" > ' + dataS[i].name + '</label>';
                        } else {
                            div.innerHTML +=
                                '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5 active" ' +
                                'id="spID' + dataS[i].idSpecialty + '" onclick="category.update(0, ' + dataS[i].idSpecialty + ')">' +
                                '<input type="radio" name="options" id="option1" > ' + dataS[i].name + '</label>';
                        }
                    }
                    //category.desc = 0;
                    //debugger;
                },
                statusCode: {
                    410: function() {
                        alert("Internal error")
                    }
                }
            });

            //var specJ = document.getElementById("spID"+data.subject.discipline.specialty.idSpecialty);
            //specJ.click();
            $("#discipline").removeAttr("disabled");
            //$("#discipline").click();

            //debugger;
            $.ajax({
                //data: category.spec,
                type: "GET",
                url: "rest/search/filter/disciplines?idSpecialty=" + data.subject.discipline.specialty.idSpecialty,
                datatype: "json",
                contentType: "application/json",
                success: function(dataD) {
                    var div = document.getElementById("DisciplineD");
                    div.innerHTML = "";
                    for(var i = 0; i < dataD.length; i++){
                        if(dataD[i].idDiscipline != data.subject.discipline.idDiscipline) {
                            div.innerHTML +=
                                '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5"' +
                                'id="diID' + dataD[i].idDiscipline + '" onclick="category.update(1, ' + dataD[i].idDiscipline + ')">' +
                                '<input type="radio" name="options" id="option1" > ' + dataD[i].name + '</label>';
                        } else {
                            div.innerHTML +=
                                '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5 active" ' +
                                'id="diID' + dataD[i].idDiscipline + '" onclick="category.update(1, ' + dataD[i].idDiscipline + ')">' +
                                '<input type="radio" name="options" id="option1" > ' + dataD[i].name + '</label>';
                        }
                    }
                    //category.subj = 0;
                },
                statusCode: {
                    404: function() {
                        var div = document.getElementById("DisciplineD");
                        div.innerHTML = "";
                    }
                }
            });

            //category.subj = data.subject.idSubject;
            $("#subject").removeAttr("disabled");
            //$("#discipline").click();
            $("#subject").click();
            $.ajax({
                //data: str,
                type: "GET",
                url: "rest/search/filter/subjects?idDiscipline=" + category.desc,
                datatype: "json",
                contentType: "application/json",
                success: function(dataS) {
                    var div = document.getElementById("collapseThree");
                    div.innerHTML = "";
                    for(var i = 0; i < dataS.length; i++){
                        if(dataS[i].idSubject != data.subject.idSubject) {
                            div.innerHTML +=
                                '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5" ' +
                                'id="suID' + (i + 1) + '" onclick="category.update(2, ' + dataS[i].idSubject + ')">' +
                                '<input type="radio" name="options" id="option1" > ' + dataS[i].name + '</label>';
                        } else {
                            div.innerHTML +=
                                '<label class="text-left btn btn-default border-fix sidebar-btn margin_bottom_5 active" ' +
                                'id="suID' + (i + 1) + '" onclick="category.update(2, ' + dataS[i].idSubject + ')">' +
                                '<input type="radio" name="options" id="option1" > ' + dataS[i].name + '</label>';
                        }
                    }
                },
                statusCode: {
                    404: function() {
                        var div = document.getElementById("collapseThree");
                        div.innerHTML = "";
                    }
                }
            });
            category.spec = data.subject.discipline.specialty.idSpecialty;
            category.desc = data.subject.discipline.idDiscipline;
            category.subj = data.subject.idSubject;

        }

    });

}


//!!!!!!!!!!!!!!!!!!!!!!!!!!CALL FUNC!!!!!!!!!!!!!!!!!!!!!!!
fillCourse(ID_COURSE);



//======================================================================================


function progressHandler(event){
    var valeur = Math.round((event.loaded / event.total) * 100);
    //alert(valeur);
    $('.progress-bar').css('width', valeur+'%').attr('aria-valuenow', valeur);

}


var freeCheckedPrice;
function setItFree(){
    if (document.getElementById("FreeCourse").checked == true){
        freeCheckedPrice = document.getElementById("coursePriceD").value;
        document.getElementById("coursePriceD").value = 0;
    }
    else{
        document.getElementById("coursePriceD").value = freeCheckedPrice;
    }
}

