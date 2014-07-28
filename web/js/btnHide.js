    function regButton(){
    var loginName=$("#inputName").val();
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