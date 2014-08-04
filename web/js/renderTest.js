var query = window.location.search;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

$.ajax({
    //data: str,
    type: "GET",
    url: "rest/questions?idLesson="+query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        document.getElementById("question").innerHTML = data.que;
        document.getElementById("answers").innerHTML = "";
        for (var i = 0; i < data.answers.length; i++) {
            document.getElementById("question").innerHTML += '<div class="radio"><label><input type="radio" name="question#0000" id="answer' + i + '" value="option1">'+data.answers[i].ans+'</label></div>';
        }
    }
});