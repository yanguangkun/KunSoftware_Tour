define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('jquery-form')($); 
	
	$(document).ready(function(){  
		$(".saveBtn").click(function(e) {
            alert('ok');
			$("#saveFrm").ajaxSubmit({
				dataType:'json', 
				success:function(data) {
					alert(data.message);
				}
			});
        });
	}); 
});