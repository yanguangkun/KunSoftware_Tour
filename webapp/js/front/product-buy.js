define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-validate')($);
	require('jquery-validate-messages')($);
	require('jquery-form')($); 
	require('datepicker');
	require('cascade'); 
	require('placeholder');  
	var lockscreen = require('lockscreen');
	  
	$(document).ready(function(){ 
		 var validate =  $("#buyFrm").validate(); 
		
		$(".nextBtn").click(function(e) { 
			
			$.getJSON("checkLogin.json" , function (data, textStatus){  
				if(data.isLogin == "0") {
					alert(data.message);
					return;
				} 
				
				var check = validate.form();  
				if(check) {
					lockscreen.lock();
				} 
				$("#buyFrm").submit();
			}); 
        });
		 
		$(".backBtn").click(function(e) { 
            history.go(-1)
        });;
		
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