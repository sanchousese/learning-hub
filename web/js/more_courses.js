$(document).ready(function(){
    $('#more_courses').click(function(){
//        alert(5);
        $.getJSON('newcourses/1.json',{},function(json){
            alert(json);
//            debugger;
        })
    })
})