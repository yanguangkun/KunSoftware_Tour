define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder'); 
	var page = 2;
	var totalPages = 2;
	$(document).ready(function(){ 
		$(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true});
		 
		$("#moreBtn").click(function(e) { 
			if(page >= totalPages) return;
			
			$("#pageNo").val(page++);
			$("#listMore").ajaxSubmit({
				dataType:'json', 
				success:function(data) { 
					$("#productList").append(data.result);
					totalPages = data.totalPages;
					 
				}
			});
		});
		
		$(".product-type li").click(function(e) {
			$(".product-type li").removeClass("active");
			$(this).addClass("active");
			page = 0; 
            $("#pageNo").val(page++);
			$("#productType").val($(this).attr("value"));
			$(".productItem").remove();
			$("#listMore").ajaxSubmit({
				dataType:'json', 
				success:function(data) { 
					$("#productList").append(data.result);
					totalPages = data.totalPages;
				}
			});
        });
	}); 
	 
	
	
});