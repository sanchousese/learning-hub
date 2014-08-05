var query = window.location.search;
if (query.substring(0, 1) == '?') {
    query = query.substring(1);
}

var loadedData = [];

$.ajax({
    //data: str,
    type: "GET",
    url: "rest/questions?idLesson=" + query,
    datatype: "json",
    contentType: "application/json",
    success: function(data) {
        loadedData = data;
        for(var i = 0; i < data.length; i++){
            ques[i] = new questionJ(data[i].que, data[i].answers[0].ans, data[i].answers[1].ans, data[i].answers[2].ans, data[i].answers[3].ans);
            document.getElementById("renderQues").innerHTML += '<li><span></span><a onclick="gotoQue('+i+')">Question '+i+'</a></li>';
        }
        restoreQ();
    }
});

function gotoQue(num){
    saveQ();
    cur=num;
    restoreQ();
}

function questionJ(q, a1, a2, a3, a4) {
    this.que = q;
    this.ans1 = a1;
    this.ans2 = a2;
    this.ans3 = a3;
    this.ans4 = a4;
    this.ans1b = false;
    this.ans2b = false;
    this.ans3b = false;
    this.ans4b = false;
}

var  ques = []; var cur = 0;

function nextQue() {
    if (cur+1 < loadedData.length) {
        saveQ();
        cur++;
        restoreQ();
    }
}

function prevQue(){
    if(cur > 0){
        saveQ();
        cur--;
        restoreQ();
    }

}

function saveQ(){
    ques[cur].ans1b = document.getElementById("answer1").checked;
    ques[cur].ans2b = document.getElementById("answer2").checked;
    ques[cur].ans3b = document.getElementById("answer3").checked;
    ques[cur].ans4b = document.getElementById("answer4").checked;
}

function restoreQ() {
    document.getElementById('qNum').innerHTML = "Вопрос " + (cur + 1) + "/" + loadedData.length;
    if (ques[cur] != null) {
        document.getElementById('question').innerHTML = ques[cur].que;
        document.getElementById('answer1v').innerHTML = ques[cur].ans1;
        document.getElementById('answer2v').innerHTML = ques[cur].ans2;
        document.getElementById('answer3v').innerHTML = ques[cur].ans3;
        document.getElementById('answer4v').innerHTML = ques[cur].ans4;
        document.getElementById("answer1").checked = ques[cur].ans1b;
        document.getElementById("answer2").checked = ques[cur].ans2b;
        document.getElementById("answer3").checked = ques[cur].ans3b;
        document.getElementById("answer4").checked = ques[cur].ans4b;
    }
}

function finishTest(){
    saveQ();
    for(var i = 0; i < loadedData.length; i++){
        loadedData[i].answers[0].correct = ques[i].ans1b;
        loadedData[i].answers[1].correct = ques[i].ans2b;
        loadedData[i].answers[2].correct = ques[i].ans3b;
        loadedData[i].answers[3].correct = ques[i].ans4b;
        //debugger;
    }
    $.ajax({
        type: "POST",
        url: "rest/questions/validateAnswers",
        data: JSON.stringify(loadedData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
            //alert(data);
            $('#myModal').modal('show');
            $(function() {
                $(".dial").knob({readOnly : true});
            });
            $('.dial').val(data).trigger('change');
        },
        statusCode: {
            401: function () {
                alert("Internal server info");
            }
        }
    });
}

function redirectTo(){
    //history.go(-1);
    $.ajax({
        //data: str,
        type: "GET",
        url: "rest/lessons/getCourseId/" + query,
        datatype: "json",
        contentType: "application/json",
        success: function(courseId) {
            window.location = "/Adminka_couse.html?" + courseId;
        }
    });
}
