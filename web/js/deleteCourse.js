/**
 * Created by Max on 06.08.2014.
 */

function deleteCourse(idCourse) {
    $.ajax({
        type: "GET",
        url: "rest/user/removeCourse?idCourse=" + idCourse,
        datatype: String,
        contentType: "text/plain",
        success: function(data) {
            window.location = "/Adminka.html";
        },
        statusCode: {
            403: function() {
            }
        }
    });
}