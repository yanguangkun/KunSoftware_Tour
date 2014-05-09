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
	
	function isPhone(value) { 
		var r = value.match(/^0?(13[0-9]|15[012356789]|18[012356789]|14[57])[0-9]{8}$/); 
		if(r==null)return false;
		else return true;
		
	} 

	$.validator.addMethod("phone", function (value, element) {  
        return this.optional(element) || isPhone(value);
    });
	
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
				if($("#combo").val() == "0") {
					$("#buyFrm").attr("action","buy3");
				}
				$("#buyFrm").submit();
			}); 
        });
		 
		$(".backBtn").click(function(e) { 
            //history.go(-1)
			$("#backFrm").submit();
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