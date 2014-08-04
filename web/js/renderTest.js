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

    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/answers?idQuestion=" + questArray[curIndex].idQuestion,
        datatype: "json",
        contentType: "application/json",
        success: function(data) {
            for (var i = 0; i < data.length; i++) {
                document.getElementById("question").innerHTML += '<div class="radio"><label><input type="radio" name="question#0000" id="answer' + i + '" value="option1">'+data[i].ans+'</label></div>';
            }
        }
    });
}

document.getElementById("prevQuestion").onclick = function (){
    curIndex--;
    if(curIndex < 0){
        curIndex = 0;
        return;
    }

    update();
}

document.getElementById("nextQuestion").onclick = function (){
    curIndex++;
    if(curIndex >= questArray.length){
        curIndex = questArray.length-1;
        return;
    }
    update();
}


document.getElementById("makeAnswer").onclick = function (){
    curIndex++;
    if(curIndex >= questArray.length){
        curIndex = questArray.length-1;
    }
    update();
}
