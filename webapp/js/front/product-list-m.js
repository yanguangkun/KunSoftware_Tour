define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder'); 
	var page = 2;
	var totalPages = 3;
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
		
		$(document).on("click",".page",function(){ 
			$.getJSON("ground.json?destination=" + $("#arriveDestination").val() + "&pageNo=" + $(this).attr("value" ), function (data, textStatus){  
				
				$(".groundInfo").html(data.result); 
			});
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