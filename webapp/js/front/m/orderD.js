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
		
		var validate =  $("#linkmanFrm").validate({  
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
		
		var validateLinkmanMobile =  $("#linkmanMobileFrm").validate({  
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
		
		$(".edit").click(function(e) {
            $(this).parent().find(".userInfo").hide();
			$(this).hide();
			$(this).parent().find(".userInput").show(); 
        });
		
		$(".cancel").click(function(e) {
            $(this).parent().parent().find(".userInfo").show();
			$(this).parent().parent().find(".edit").show();
			$(this).parent().parent().find(".userInput").hide();
        });
		
		$(".linkmanBtn").click(function(e) { 
			
			var check = validate.form();  
			if(check) {
				lockscreen.lock();
			} 
			$(this).parent().parent().find(".userInfo").text($("#linkman").val()); 
			$("#linkmanFrm").submit();		
        });
		
		$(".linkmanMobileBtn").click(function(e) { 
			
			var check = validateLinkmanMobile.form();  
			if(check) {
				lockscreen.lock();
			} 
			$(this).parent().parent().find(".userInfo").text($("#linkmanMobile").val());
			$("#linkmanMobileFrm").submit();
        });
		
		$(".editUser").click(function(e) {
            $('#userInfo').modal({backdrop: 'static'});
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