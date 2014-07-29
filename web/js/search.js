  function searchButton() {
          var keyWords = $('#searchField').val();
        alert(keyWords);
//        returnCourses(keyWords);
    }



//function returnCourses(keyWords) {// pass your data in method
//          $.ajax({
//              type: "POST",
//              url: "http://localhost:8080/rest/search/courses/byKeywords",  ///search/courses/byKeywords
//              data: JSON.stringify(keyWords),
//              contentType: "application/json; charset=utf-8",
//              dataType: "json",
//                  statusCode: {
//                  200: function () {
//                      $("#register-btn").hide('slow', function() {});
//                      $("#hideRegisterForm").hide('slow', function() {});
//                      $('#logout').css('display', 'block');
//                      $('#login').click();
//                      $('#user-info').css('display', 'block');
//                      $.ajax({
//                          //data: str,
//                          type: "POST",
//                          url: "http://localhost:8080/rest/user/userInfo",
//                          datatype: "json",
//                          contentType: "application/json",
//                          success: function(data) {
//                              document.getElementById("user-info").innerHTML = data.login;
//
//                          },
//                          statusCode: {
//                              403: function() {
//                                  alert("Internal error");
//                              }
//                          }
//                      });
//                  },
//                  401: function () {
//                      alert("Invalid login or password");
//                  }
//              }