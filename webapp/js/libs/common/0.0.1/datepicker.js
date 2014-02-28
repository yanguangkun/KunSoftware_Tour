define(function(require, exports, module) {
		
	var $ = require('jquery'); 
	var wdatepicker = require('wdatepicker'); 
	$(document).ready(function(){ 
		$(".date,.dateValid").click(function(e) {
			var options = {};
			if(typeof($(this).attr("minDate")) != "undefined") {
				options.minDate = $(this).attr("minDate");
			}
			
			if(typeof($(this).attr("maxDate")) != "undefined") {
				options.maxDate = $(this).attr("maxDate");
			}
			
			if(typeof($(this).attr("dateFmt")) != "undefined") {
				options.dateFmt = $(this).attr("dateFmt");
			}
			
			if(typeof($(this).attr("doubleCalendar")) != "undefined") {
				options.doubleCalendar = $(this).attr("doubleCalendar");
			}
			   
			wdatepicker(options); 
		});
	}); 
});