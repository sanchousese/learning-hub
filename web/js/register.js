		$("document").ready(function(){
		    $('#modal').modal();
		});

        function registerButton() {
            debugger;
            var User = {
                login: $('#registerLogin').val(),
                pass: $('#registerPassword').val(),
                email: $('#registerEmail').val()
            };
            addRegisterData(User)
        }

        function addRegisterData(data){// pass your data in method
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/rest/user/addUser",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json",
                statusCode :{
                    200: function () {
//                   alert("success...");
                        document.location.href = "http://google.com";
                    },
                    400: function() {
                        alert("Invalid e-mail address. Please, try again.");
                    }
                }
            });
        }