define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder');
	require('jquery-validate')($);
	require('jquery-validate-messages')($);   
	var lockscreen = require('lockscreen');
	$(document).ready(function(){ 
		 var validate =  $("#loginFrm").validate(); 
		 
		$("#loginBtn").click(function(e) { 
            var check = validate.form();  
			 
			if(check) {
				lockscreen.lock();
			} 
			$("#loginFrm").submit();
			return false;
        });
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