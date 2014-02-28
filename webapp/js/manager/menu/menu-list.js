define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('page'); 
	$(document).ready(function(){  
		$(".searchBtn").click(function(e) {
            $("#searchForm").submit();
        });
		$(".del").click(function(e) {
			var url = $(this).attr("href");
			var that = this;
			$.get(url, function(data){
				$(that).parent("td").parent("tr").remove(); 
			});  
			return false;
        });
	}); 
});