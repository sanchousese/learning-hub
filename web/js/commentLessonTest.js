/**
 * Created by Max on 05.08.2014.
 */
var ID_LESSON = 1;

function showAllCommentsLesson() {
    var commentBox = document.getElementById("commentOBox");

    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/commentLesson/byLesson?idLesson=" + ID_LESSON,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            commentBox.innerHTML = "";

            for(var i = 0; i < data.length; i++){
                var str = data[i].body + "<br><br>";


                commentBox.innerHTML += str;
            }
        },
        statusCode: {
            404: function() {
                commentBox.innerHTML = "";
            }
        }
    });

    //alert("Hello, World!");
}

function insertCommentLesson() {
    var commentBox = document.getElementById("commentIBox");

    var commentVar = {
        body: commentBox.value,
        idLesson: ID_LESSON
    }

    $.ajax({
        //data: str,
        type: "POST",
        url: "rest/commentLesson/create",
        data: JSON.stringify(commentVar),
        contentType: "application/json",
        dataType: "json",
        success: function() {
        },
        statusCode: {
            401: function() {
                alert("insert error");
            }
        }
    });


}
