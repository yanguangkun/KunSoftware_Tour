define(function(require, exports, module) {
		
	var $ = require('jquery');  
	var lockscreen = require('lockscreen'); 
	$(document).ready(function(){
		$(document).on("change",".cascade",function(){ 
			var that = this;
			var url = Context_Path + "/manager/cascade/list.json";
			var cascadeId = $(this).attr("cascadeId");
			var cascadeParam = $(this).attr("cascadeParam");
			
			var cascadeParams = cascadeParam.split(',');
			var cascadeValue = "";
			
			for(i = 0;i < cascadeParams.length;i++) {
				if(cascadeValue != "") cascadeValue += ",";
				cascadeValue = $(cascadeParams[i]).val();
			}
			 
			var defaultValue = $(cascadeId).attr("defaultValue");
			var cascadeCode = $(cascadeId).attr("cascadeCode");;
			$.ajax({
				type: 'POST',
				url: url,
				data: {'cascadeCode':cascadeCode,'cascadeValue':cascadeValue,'defaultValue':defaultValue},
				dataType: 'json',
				success:function(data) {
					$(cascadeId).empty();
					$(cascadeId).append("<option value=\"\">-请选择-</option>");
					$(cascadeId).append(data.result);
				} 
			});
		}); 
		$(".cascade").trigger('change');
	});
	
	 
});