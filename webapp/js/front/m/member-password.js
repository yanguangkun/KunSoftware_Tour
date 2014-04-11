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
		var validate =  $("#saveFrm").validate({ 
			rules: { 
				newpassword2: {
					required: true,
					minlength: 6,
					equalTo: "#newpassword"
				}
			},
			messages: { 
				newpassword2: {
					required: "没有确认密码",
					minlength: "确认密码不能小于{0}个字符",
					equalTo: "两次输入密码不一致嘛"
				}
			},
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
		
		$(".saveBtn").click(function(e) { 
			
			var check = validate.form();  
			if(check) {
				lockscreen.lock();
			} 
			$("#saveFrm").submit();
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