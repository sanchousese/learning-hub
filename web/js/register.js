//		$("document").ready(function(){
//		    $('#modal').modal();
//		});

        function registerButton() {
            var User = {
                login: $('#inputName').val(),
                pass: $('#inputPassword').val(),
                email: $('#inputEmail').val()
            };
            addUser(User)
        }

        function addUser(data) {// pass your data in method
            $.ajax({
                type: "POST",
                url: "rest/user/addUser",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                statusCode: {
                    200: function () {
                        alert("Success...");
                    },
                    401: function () {
                        alert("Invalid login or password");
                    }
                }
            });
        }