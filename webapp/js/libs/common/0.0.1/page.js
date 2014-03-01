define(function(require, exports, module) {
		
	var $ = require('jquery');   
	$(document).ready(function(){
		
		$("#cli_page").keypress(function (e) {
			var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
			if(keyCode == 13) {
			  page($(this).val());
			}
		});
		
		$("#cli_page").focus(function(){
			this.select();
		}); 
	
		$(".defaultPage").click( function () {
			 
			if($(this).attr("page")) {
				page($(this).attr("page"));
			} 
		}); 	
		
		$("img").mousemove( function () {
			if($(this).attr("page")) {
				$(this).css({'cursor':'hand'}); 
				if($(this).attr("type") == "1") {
					$(this).attr("src",Context_Path + "/images/pagination-arrow-first-hover.gif"); 
				} else if($(this).attr("type") == "2") { 
					$(this).attr("src",Context_Path + "/images/pagination-arrow-prev-hover.gif"); 
				} else if($(this).attr("type") == "3") { 
					$(this).attr("src",Context_Path + "/images/pagination-arrow-next-hover.gif"); 
				}
			}	
		}); 
		
		$("img").mouseout( function () {
			if($(this).attr("page")) { 
				if($(this).attr("type") == "1") {			 
					$(this).attr("src",Context_Path + "/images/pagination-arrow-first.gif");
				} else if($(this).attr("type") == "2") { 
					$(this).attr("src",Context_Path + "/images/pagination-arrow-prev.gif");
				} else if($(this).attr("type") == "3") { 
					$(this).attr("src",Context_Path + "/images/pagination-arrow-next.gif");
				}
			}	
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