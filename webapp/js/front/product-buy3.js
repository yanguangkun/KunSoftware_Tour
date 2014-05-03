define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	 
	require('placeholder');  
	  
	$(document).ready(function(){ 
		 
		
	}); 
	 
	$(".indexDestination").mouseenter(function(e) {
        $(".arr").show();
		$(".indexDestinationList").show();
    });
	
	$(".indexDestination").mouseleave(function(e) {
        $(".arr").hide();
		$(".indexDestinationList").hide();
    });
	 
});