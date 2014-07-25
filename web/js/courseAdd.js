function CCategory(){
    this.getSpecialty = function(){
        $.ajax({
            //data: str,
            type: "POST",
            url: "http://localhost:8080/rest/course/getSpecialty",
            datatype: "json",
            contentType: "application/json",
            success: function(data) {
                alert(data[0].name);
            },
            statusCode: {
                410: function() {
                    alert("Internal error")
                }
            }
        });
    }
}

var category = new CCategory();
category.getSpecialty();