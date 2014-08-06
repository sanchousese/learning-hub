/**
 * Created by Max on 05.08.2014.
 */
/*

var query = window.location.search;
//debugger;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

var ID_LESSON = query;
*/




var xxx;
function getIdLson() {

    return xxx;
}

function setIdLson(yyy) {
    xxx = yyy;
}

var commentOBox = document.getElementById("commentOBox");

var lid;
//showAllCommentsLesson();
function showAllCommentsLesson(ID_LESSON) {
    setIdLson(ID_LESSON);
    var commentOBox = document.getElementById("commentOBox");

    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/commentLesson/byLesson?idLesson=" + ID_LESSON,
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
                commentBox.innerHTML = "";
            }
        }
    });

    //alert("Hello, World!");
}

function insertCommentLesson(ID_LESSON) {
    var commentBox = document.getElementById("post_field");

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

        },
        statusCode: {
            401: function() {
                alert("insert error");
            }
        }
    });
}


function addZeroDate(x) {
    if (x < 10) {
        x = "0" + x;
    }
    return x;
}