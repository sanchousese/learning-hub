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
              url: "http://localhost:8080/rest/user/login",
              data: JSON.stringify(data),
              contentType: "application/json; charset=utf-8",
              dataType: "json",
              statusCode: {
                  200: function () {
                      $("#register-btn").hide('slow', function() {});
                      $("#hideRegisterForm").hide('slow', function() {});
                      $('#logout').css('display', 'block');
                      $('#login').click();
                      $('#login').click();
                      $('#login').click();
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
              url: "http://localhost:8080/rest/user/logout",
              contentType: "application/json; charset=utf-8",
              statusCode: {
                  200: function () {
                      $("#logout").hide('slow', function() {});
                      $('#register-btn').css('display', 'block');
                      $('#hideRegisterForm').css('display', 'block');
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
          url: "http://localhost:8080/rest/user/userInfo",
          datatype: "json",
          contentType: "application/json",
          success: function(data) {
              $("#register-btn").hide();
              $("#hideRegisterForm").hide();
              $('#logout').css('display', 'block');
              $('#login').click();
              $('#login').click();
              $('#login').click();
              //$('#user-info').innerHTML += data.login;
              //$('#user-info').css('display', 'inlineblock');

          },
          statusCode: {
              403: function() {
                  $("#logout").hide('slow', function() {});
                  $('#register-btn').css('display', 'block');
                  $('#hideRegisterForm').css('display', 'block');
              }
          }
      });