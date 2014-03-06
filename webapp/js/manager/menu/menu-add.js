define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('jquery-validate')($);
	require('jquery-validate-messages')($);
	require('datepicker');
	var juicer = require('juicer'); 
	var lockscreen = require('lockscreen');
	
	$(document).ready(function(){  
		
		var json = {
			value: '<strong>juicer</strong>'
		};

		var escape_tpl='${value}';
		var unescape_tpl='$${value}';
		 
		//alert(juicer(escape_tpl, json)); //输出 '&lt;strong&gt;juicer&lt;/strong&gt;'
		//alert(juicer(unescape_tpl, json)); //输出 '<strong>juicer</strong>'

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
			lockscreen.lock();
			$("#saveFrm").submit();
			/*$("#saveFrm").ajaxSubmit({
				dataType:'json', 
				success:function(data) { 
					alert(data.message);
					if(data.success == '1') {
						$("#id").val(data.id);
					}
				}
			});*/
        });
	}); 
});