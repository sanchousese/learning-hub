function CCategory(){
    var spec = 0
    var desc = 0
    var subj = 0
    this.getSpecialty = function(){
        $.ajax({
            //data: str,
            type: "POST",
            url: "http://localhost:8080/rest/course/getSpecialty",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("SpecialtyD");
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<a href="#"  class="list-group-item padding-fix border-fix panel-dropdown" onclick="category.update(0, '+ i +')">'+ data[i].name+ '</a>'
                }
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
            //data: str,
            type: "POST",
            url: "http://localhost:8080/rest/course/getDiscipline",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("DisciplineD");
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<a href="#"  class="list-group-item padding-fix border-fix panel-dropdown" onclick="category.update(1, '+ i +')">'+ data[i].name+ '</a>'
                }
            },
            statusCode: {
                410: function() {
                    alert("Internal error")
                }
            }
        });
    }
    this.getSubject = function(){
        $.ajax({
            //data: str,
            type: "POST",
            url: "http://localhost:8080/rest/course/getSubject",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                var div = document.getElementById("SubjectD");
                for(var i = 0; i < data.length; i++){
                    div.innerHTML +=
                        '<a href="#"  class="list-group-item padding-fix border-fix panel-dropdown" onclick="category.update(2, '+ i +')">'+ data[i].name+ '</a>'
                }
            },
            statusCode: {
                410: function() {
                    alert("Internal error")
                }
            }
        });
    }
    this.update = function(type, index){
        if(type == 0){
            //alert("update " + index);
            category.spec = index;
            $("#discipDHide").click();
        }
        if(type == 1){
            //alert("update 1" + index);
            category.desc = index;
            $("#subjDHide").click();
        }
        if(type == 2){
            //alert("update " + index);
            category.subj = index;
        }
    }
}

var category = new CCategory();
category.getSpecialty();
category.getDiscipline();
category.getSubject();

function AddCourseInfo() {
    if ($("#courseNameD").val() != "" && $("#courseDescD").val() != "" && $("#coursePriceD").val() != "") {
        //alert($("#courseNameD").val());
        var course = {
            name: $("#courseNameD").val(),
            description: $("#courseDescD").val(),
            price: $("#coursePriceD").val()
        };
        $.ajax({
            data: JSON.stringify(course),
            type: "POST",
            url: "http://localhost:8080/rest/course/create",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                //alert("Course added");
                window.location.href = "index.html";

            },
            statusCode: {
                401: function() {
                    alert("Internal error");
                }
            }
        });
    }
}
