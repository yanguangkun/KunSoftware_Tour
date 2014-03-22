define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('jquery-validate')($);
	require('jquery-validate-messages')($); 
	require('datepicker');
	var lockscreen = require('lockscreen');
	var modaldialog = require('modaldialog'); 
	
	$(document).ready(function(){ 
		
		var validate =  $("#saveFrm").validate({ 
			submitHandler: function(form) { 
				$(form).ajaxSubmit({
					dataType:'json', 
					success:function(data) {
						alert(data.message);
						if(data.success == '1') {
							$("#id").val(data.id);
						}
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
		
		$(".dialogBtn").click(function(e) { 
			modaldialog({code:'gallery',obj:this});
		});
	}); 
	 
});