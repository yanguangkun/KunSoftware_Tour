define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	
	$(document).ready(function(){  
		$(".saveBtn").click(function(e) { 
			$("#saveFrm").ajaxSubmit({
				dataType:'json', 
				success:function(data) {
					alert(data.message);
				}
			});
        });
	}); 
});