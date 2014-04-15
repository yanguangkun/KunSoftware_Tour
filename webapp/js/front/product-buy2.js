define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-validate')($);
	require('jquery-validate-messages')($);
	require('jquery-form')($); 
	require('datepicker');
	require('cascade'); 
	var lockscreen = require('lockscreen');
	  
	$(document).ready(function(){ 
		 var validate =  $("#buyFrm").validate({ 
		 
			submitHandler: function(form) { 
				$(form).ajaxSubmit({
					dataType:'json', 
					success:function(data) {
						alert(data.message); 
						lockscreen.unLock();
					}
				});
			}	
		}); 
		 
		
		$(".nextBtn").click(function(e) { 
			
			var check = validate.form();  
			if(check) {
				lockscreen.lock();
			} 
			$("#buyFrm").submit();
        });
		 
		$(".backBtn").click(function(e) { 
            location.href = "detail?id=" + $("#id").val();
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