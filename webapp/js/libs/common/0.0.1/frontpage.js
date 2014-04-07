define(function(require, exports, module) {
		
	var $ = require('jquery');   
	$(document).ready(function(){ 
		$(".page").click(function(e) {
            page($(this).attr("value"));
			return false;
        });
	}); 
	
	function page(v) {
		
		
		if(typeof pageFrm == "undefined") {
			location.href = "?pageNo=" + v;
		} else {		
			pageFrm.pageNo.value = v;
			pageFrm.submit();
		}
	}
});