$(document).ready(function(){
         $("#searchField").keypress(function(e){
           if(e.keyCode==13){
                searchByKeywords();
           }
         });
});