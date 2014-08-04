      function submitButton() {
          var Auth = {
            login: $('#loginField').val(),
            password: $('#password').val()
        };
        addData(Auth)
    }

      function addData(data) {// pass your data in method
          $.ajax({
              type: "POST",
              url: "rest/login",
              data: JSON.stringify(data),
              contentType: "application/json; charset=utf-8",
              dataType: "json",
              statusCode: {
                  200: function () {
                      $("#register-btn").hide('slow', function() {});
                      $("#hideRegisterForm").hide('slow', function() {});
                      $('#logout').css('display', 'block');
                      $('#login').click();
                      $('#user-info').css('display', 'block');
                      $.ajax({
                          //data: str,
                          type: "POST",
                          url: "rest/userInfo",
                          datatype: "json",
                          contentType: "application/json",
                          success: function(data) {
                              document.getElementById("user-info").innerHTML = data.login;
                          },
                          statusCode: {
                              403: function() {
                                  alert("Internal error");
                              }
                          }
                      });
                  },
                  401: function () {
                      alert("Invalid login or password");
                  }
              }
          });
      }

      function logoutButton() {
          $.ajax({
              type: "POST",
              url: "rest/logout",
              contentType: "application/json; charset=utf-8",
              statusCode: {
                  200: function () {
                      $("#logout").hide('slow', function() {});
                      $('#register-btn').css('display', 'block');
                      $('#hideRegisterForm').css('display', 'block');
                      $('#user-info').css('display', 'none');
                  },
                  404: function () {
                      alert("Invalid request");
                  }
              }
          });
      }

      $.ajax({
          //data: str,
          type: "POST",
          url: "rest/userInfo",
          datatype: "json",
          contentType: "application/json",
          success: function(data) {
              $("#register-btn").hide();
              $("#hideRegisterForm").hide();
              $('#logout').css('display', 'block');
              $('#login').click();
              $('#login').click();
              //alert(data.login);
              //$('#user-info').childNodes[0].nodeValue  = data.login;
              document.getElementById("user-info").innerHTML = data.login;
              //document.getElementById("userNameShowing").innerHTML = data.login;
              $('#user-info').css('display', 'block');

          },
          statusCode: {
              403: function() {
                  $("#user-info").hide();
                  $("#logout").hide();
                  $('#register-btn').css('display', 'block');
                  $('#hideRegisterForm').css('display', 'block');

              }
          }
      });