define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder');  
	require('jquery-validate')($);
	require('jquery-validate-messages')($); 
	require('frontpage');
	var lockscreen = require('lockscreen');
	$(document).ready(function(){ 
		 var validate =  $("#saveFrm").validate({ 
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
		
		$("#saveBtn").click(function(e) { 
            var check = validate.form();  
			 
			if(check) {
				lockscreen.lock();
			} 
			//$("#saveFrm").submit();
			return false;
        });
		
		$("#customize-info1").click(function(e) {
            location.href = "info1";
        });
		
		$(".destination").click(function(e) {
            location.href = "info2?destination=" + $(this).attr("value");
        });
		
	}); 
	
	$(".destinationParent").mouseenter(function(e) {
        $(".destinationChild").show();
    });
	
	$(".destinationMain").mouseleave(function(e) {
        $(".destinationChild").hide();
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