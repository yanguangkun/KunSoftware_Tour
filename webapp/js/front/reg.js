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
		 var validate =  $("#regFrm").validate({ 
		 	rules: { 
			    userName:{
				    required: true,
					minlength: 4,
				},
				email:{
				    required: true,
					minlength: 6,
				},
				password: {
					required: true,
					minlength: 6 
				},
				password2: {
					required: true,
					minlength: 6,
					equalTo: "#password"
				}
			},
			messages: { 
				userName:{
					minlength:"请输入4到20位字母或数字"
				},
				password2: {
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
		 
	 
		
		$("body").bind('keyup',function(event) {
			if(event.keyCode==13){  
				$("#regBtn").trigger("click");
			}   
		}); 

		$("#regBtn").click(function(e) { 
            var check = validate.form();  
			 
			if(check) {
				lockscreen.lock();
			} 
			$("#regFrm").submit();
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