/**
 * Created by Max on 05.08.2014.
 */

var query = window.location.search;
//debugger;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}


var ID_COURSE = query;
var commentOBox = document.getElementById("commentOBox");

function addZeroDate(x) {
    if (x < 10) {
        x = "0" + x;
    }
    return x;
}
function showAllComments() {

    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/comment/byCourse?idCourse=" + ID_COURSE,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            commentOBox.innerHTML = "";

            for(var i = 0; i < data.length; i++){
                var pubDate =  (new Date(data[i].date));

                var minutest = addZeroDate(pubDate.getMinutes());

                var day = addZeroDate(pubDate.getDay());

                var month = addZeroDate(pubDate.getMonth());

                var formattedDate = day + "/" + month + "/" + pubDate.getFullYear() + ' @ ' + pubDate.getHours() + ":" + minutest;


                commentOBox.innerHTML += '<div class="comment comment-sent"><p>' +
                    data[i].body +
                    '</p><h4 class="date">' + formattedDate + '</h4>' +
                '</div>';
            }
        },
        statusCode: {
            404: function() {
                commentOBox.innerHTML = "";
            }
        }
    });

    //alert("Hello, World!");
}

showAllComments();

function insertComment() {
    var commentBox = document.getElementById("post_field");

    var commentVar = {
        body: commentBox.value,
        idCourse: ID_COURSE
    }

    $.ajax({
        //data: str,
        type: "POST",
        url: "rest/comment/create",
        data: JSON.stringify(commentVar),
        contentType: "application/json",
        dataType: "json",
        success: function() {
            // clearing the field
            commentBox.value = "";


            // adding new comment to all comments
            var trash = commentOBox.innerHTML;

            //getting current comment time
            var currentdate = new Date();
            var datetime = "Your last comment: "
                + addZeroDate(currentdate.getDate()) + "/"
                + addZeroDate(currentdate.getMonth() + 1)  + "/"
                + addZeroDate(currentdate.getFullYear()) + " @ "
                + addZeroDate(currentdate.getHours()) + ":"
                + addZeroDate(currentdate.getMinutes());

            var newComment = '<div class="comment comment-sent"><p>' +
                commentVar.body +
                '</p><h4 class="date">' + datetime + '</h4>' +
                '</div>';

            commentOBox.innerHTML = newComment + trash;

//            window.location.href = "CourseComment.html";

        },
        statusCode: {
            401: function() {
                alert("insert error");
            }
        }
    });


}
