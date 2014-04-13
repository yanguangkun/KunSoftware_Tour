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
		
		var travelEditValidate =  null;
		
		$(document).on("click",".saveBtn",function(){
			
			var check = travelEditValidate.form();  
			if(check) {
				lockscreen.lock();
			} 
			$("#saveFrm").submit();
			return false;
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
			lockscreen.lock();
			$.get("orders-travel-edit?id=" + $(this).attr("value"),function(data) {
				 
				lockscreen.unLock();
				$("#userBody").html(data);
				$('#userInfo').modal({backdrop: 'static'});	
				
				travelEditValidate = $("#saveFrm").validate({ 
					submitHandler: function(form) { 
						$(form).ajaxSubmit({
							dataType:'json', 
							success:function(data) {
								alert(data.message); 
								location.reload();
							}
						});
					}	
				}); 
			});
           
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