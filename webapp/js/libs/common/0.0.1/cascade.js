define(function(require, exports, module) {
		
	var $ = require('jquery');  
	var lockscreen = require('lockscreen'); 
	$(document).ready(function(){ 
		$(document).on("change",".cascade",function(){ 
			var that = this;
			var url = Context_Path + "/manager/cascade/list.json";
			var cascadeIds = $(this).attr("cascadeId");
			var cascadeParam = $(this).attr("cascadeParam");
			 
			var cascadeParams = cascadeParam.split(',');
			var cascadeValue = "";
			 
			for(i = 0;i < cascadeParams.length;i++) {
				if(cascadeValue != "") cascadeValue += ",";
				cascadeValue += $(cascadeParams[i]).val();
			}
			
			var cascadeIdArray = cascadeIds.split(","); 
			var cascadeId = "";
			var defaultValue = "";
			var cascadeCode = ""; 
			for(i = 0;i < cascadeIdArray.length;i++) {
				cascadeId = cascadeIdArray[i]; 
				defaultValue = $(cascadeId).attr("defaultValue");
				cascadeCode = $(cascadeId).attr("cascadeCode");
				$.ajax({
					type: 'POST',
					url: url,
					data: {'cascadeId':cascadeId,'cascadeCode':cascadeCode,'cascadeValue':cascadeValue,'defaultValue':defaultValue},
					dataType: 'json',
					success:function(data) {
						if(data.cascadeCode == 'tag') {
							$(data.cascadeId).html(data.result);
						} else {
							$(data.cascadeId).empty();
							$(data.cascadeId).append("<option value=\"\">-请选择-</option>");
							$(data.cascadeId).append(data.result);
							if($(data.cascadeId).attr("cascadeTrigger") == "true") {alert(data.cascadeId);
								$(data.cascadeId).trigger();
							}
						}
					} 
				});
			}
			
		}); 
		$(".cascade").trigger('change');
	});
	
	 
});