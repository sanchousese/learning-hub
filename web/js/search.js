var searchObject = {
    // keywords: ["Json", "php"],
    keywords: "",
    sortType: "SORT_BY_POPULARITY",
    searchType: "SEARCH_WITHOUT_FILTER",
    idSpecialty: 0,
    idDiscipline: 0,
    idSubject: 0
};

searchByKeywords();

function searchByKeywords(){
    var keyWords = $('#searchField').val();

    var selcetedOption = document.getElementById("sortDropdown");
    var sortType = selcetedOption.options[selcetedOption.selectedIndex].value;


    searchObject.keywords = keyWords;
    searchObject.sortType = sortType;

// TODO: mouse scroll event
/*
    searchObject.idFrom = 1;
    searchObject.idTo = 5;
*/


    getCoursesCatalog(searchObject, 1);
}

// TODO: mouse scroll event
/*
function getNextPackOfCourses() {
    var COUNT_OF_LOADED_COURSES = 4;
    searchObject.idFrom += COUNT_OF_LOADED_COURSES;
    searchObject.idTo += COUNT_OF_LOADED_COURSES;
}*/

function getCoursesCatalog(searchObj, rewrite) {
    $.ajax({
        //data: str,
        type: "POST",
        //    url: "http://localhost:8080/rest/course/getAll",
        url: "rest/search/courses",
        data: JSON.stringify(searchObj),
        contentType: "application/json",
        dataType: "json",
        success: function(data) {
            if (rewrite > 0) {
                document.getElementById("courseContainer").innerHTML = "";
            }
            else {
                // pass
            }

            var div = document.getElementById("courseContainer");

            for (var i = 0; i < data.length; i++) {
                div.innerHTML +=
                    '<div class="col-xs-12 col-sm-6 col-md-6  placeholder padding_5" href="/Course.html">' +
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
        },
        statusCode: {
            404: function() {
                document.getElementById("courseContainer").innerHTML = "";
            }

        }
    });
}

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
                        '<label class="text-left btn btn-default border-fix sidebar-btn mardgin_bottom_5" onclick="category.update(0, '+ data[i].idSpecialty +')">' +
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
        $.ajax({
            //data: category.spec,
            type: "GET",
            url: "rest/search/filter/disciplines?idSpecialty=" + category.spec,
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("collapseTwo");//DisciplineD
                div.innerHTML = "";
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<label class="text-left btn btn-default border-fix sidebar-btn mardgin_bottom_5" onclick="category.update(1, '+ data[i].idDiscipline +')">' +
                        '<input type="radio" name="options" id="option1" > '+ data[i].name+ '</label>';
                }
                category.subj = 0;
            },
            statusCode: {
                404: function() {
                    var div = document.getElementById("collapseTwo");
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
                var div = document.getElementById("collapseThree");//collapseThree
                div.innerHTML = "";
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<label class="text-left btn btn-default border-fix sidebar-btn mardgin_bottom_5" onclick="category.update(2, '+ data[i].idSubject +')">' +
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
        document.getElementById("courseContainer").innerHTML = "";

        if(type == 0){
            searchObject.searchType = "SEARCH_BY_SPECIALTY";
            searchObject.idSpecialty = index;
            //alert("update " + index);
            category.spec = index;
            $("#discipline").removeAttr("disabled");
            $("#discipline").click();
            category.desc = 0;
            category.getDiscipline();
            category.getSubject();

        }
        if(type == 1){
            searchObject.searchType = "SEARCH_BY_DISCIPLINE";
            searchObject.idDiscipline = index;
            //alert("update 1" + index);
            category.desc = index;
            //$("#subjDHide").click();
            $("#subject").removeAttr("disabled");
            $("#subject").click();
            category.subj = 0;
            category.getSubject();

        }
        if(type == 2){
            searchObject.searchType = "SEARCH_BY_SUBJECT";
            searchObject.idSubject = index;
            //alert("update " + index);
            category.subj = index;

        }
        searchByKeywords();

    }
}

function openCourse( indexOfCourse ){
    window.location = "/Course.html?" + indexOfCourse;
}


var category = new CCategory();
category.getSpecialty();
