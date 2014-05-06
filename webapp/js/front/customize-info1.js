define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder');  
	require('jquery-validate')($);
	require('jquery-validate-messages')($); 
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
			$("#saveFrm").submit();
			return false;
        });
		
		$("#customize-info2").click(function(e) {
            location.href = "info2";
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
	
	$(".destinationParent").mouseenter(function(e) {
        $(".destinationChild").show();
    });
	
	$(".destinationMain").mouseleave(function(e) {
        $(".destinationChild").hide();
    });
	
	$(".destination").click(function(e) {
		$("#arriveDestination").val($(this).attr("value"));
		$("#arriveDestinationName").val($(this).text());
		$(".destinationChild").hide();
		return false;
    });
});