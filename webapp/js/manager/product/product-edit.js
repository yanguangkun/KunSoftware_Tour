define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-validate')($);
	require('jquery-validate-messages')($);
	require('jquery-form')($); 
	var lockscreen = require('lockscreen');
	var modaldialog = require('modaldialog');
	
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
		
		$(".saveBtn").click(function(e) { 
			var check = validate.form();  
			if(check) {
				lockscreen.lock();
			} 
			$("#saveFrm").submit();
        });
		
		$("#destinationImageFile").change(function(e) { 
            $("#imagePathShow").attr("src",$(this).val());
        });
		
		$(".dialogBtn").click(function(e) { 
			modaldialog({code:'gallery',obj:this});
		});
	}); 
});