var query = window.location.search;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

var questArray;
var curIndex = 0;

$.ajax({
    //data: str,
    type: "GET",
    url: "rest/questions?idLesson=" + query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        questArray = data;
        update();

    }
});

function update() {
    document.getElementById("question").innerHTML = questArray[curIndex].que;
    document.getElementById("answers").innerHTML = "";
    for (var i = 0; i < questArray[curIndex].answers.length; i++) {
        document.getElementById("question").innerHTML += '<div class="radio"><label><input type="radio" name="question#0000" id="answer' + i + '" value="option1">'+questArray[curIndex].answers[i].ans+'</label></div>';
    }
}

document.getElementById("prevQuestion").onclick = function (){
    curIndex--;
    update();
}

document.getElementById("nextQuestion").onclick = function (){
    curIndex++;
    update();
}


document.getElementById("makeAnswer").onclick = function (){
    curIndex++;
    update();
}
