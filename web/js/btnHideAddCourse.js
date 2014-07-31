/**
 * Created by Sania_000 on 7/31/2014.
 */
function addCourseButton(){
    var loginName=$("#courseNameD").val();
    var mail=$("#inputEmail").val();
    var pass=$("#inputPassword").val();
    var passConfirm=$("#inputPasswordConfirm").val();
    if (loginName !=="" && mail !=="" && pass !=="" && passConfirm !=="") {
        $("#reg-btn").removeAttr("disabled");
    }
    else {
        $("#reg-btn").attr("disabled","disabled");
    }
}