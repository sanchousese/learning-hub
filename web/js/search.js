var searchObject = {
    // keywords: ["Json", "php"],
    keywords: "",
    searchType: "SEARCH_BY_KEYWORDS",
    sortType: "SORT_BY_POPULARITY"
};

function searchByKeywords(){
    var keyWords = $('#searchField').val();
    //var searchType = ()())(()()()90
    searchObject.keywords = keyWords;

    getCoursesCatalog(searchObject);
}

function sortByPrice() {

}

function sortByPopularity() {

}

function sortByDate() {

}



function getCoursesCatalog(searchObj) {
    $.ajax({
        //data: str,
        type: "POST",
        //    url: "http://localhost:8080/rest/course/getAll",
        url: "rest/search/courses",
        data: JSON.stringify(searchObj),
        contentType: "application/json",
        dataType: "json",
        success: function(data) {
            var div = document.getElementById("courseContainer");

            for (var i = 0; i < data.length; i++) {
                div.innerHTML +=
                    '<div class="col-xs-12 col-sm-6 col-md-6  placeholder padding_5" href="/Course.html">' +
                    '<div class="course_big">' +
                    '<div class="news_cover"><a href="javascript:openCourse(' + data[i].idCourse + ');">' +
                    '<img id = "courseMainImage'+ i +'" src="../img/image.jpg" alt="Course img" class="img-rounded"></a></div>' +
                    '<div class="news_content">' +
                    '<h3><a href="javascript:openCourse(' + data[i].idCourse + ');">' + data[i].name + '</a></h3>' +
                    '<p>' + data[i].description + '</p>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
            }
            for (var i = 0; i < data.length; i++) {
                $("#courseMainImage"+i).attr('src','rest/course/getLogoImage/'+(data[i].idCourse));
            }
        }
    });
}