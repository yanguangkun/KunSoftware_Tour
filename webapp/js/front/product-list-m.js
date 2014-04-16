define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder'); 
	var page = 2;
	var totalPages = 3;
	$(document).ready(function(){ 
		$(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true,interTime:5000});
		 
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
		
		$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + $("#banner").val()+ "&r=" + Math.random() , function (data, textStatus){  
			$(".questionsInfo").html(data.result); 
		});
		
		$(document).on("click",".questionsInfo .page",function(){ 
			$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + $("#banner").val() + "&pageNo=" + $(this).attr("value" ) + "&r=" + Math.random(), function (data, textStatus){  
				
				$(".questionsInfo").html(data.result);
			});
		});
		
		$("#questionsBtn").click(function(e) {
            
			$.post("questionsSave.json",{
				'destination':$("#destination").val(),
				'banner':$("#banner").val(),
				'content':$("#content").val()	
			},function(data) {
				alert(data.message);
			},"json");
			
			return false;
        });
		 
	}); 
	
	$(".destinationParent").mouseenter(function(e) {
        $(".destinationChild").show();
    });
	
	$(".destinationMain").mouseleave(function(e) {
        $(".destinationChild").hide();
    });
	
	 
	$(".indexDestination").mouseenter(function(e) {
        $(".arr").show();
		$(".indexDestinationList").show();
    });
	
	$(".indexDestination").mouseleave(function(e) {
        $(".arr").hide();
		$(".indexDestinationList").hide();
    });
	
	$(".destination").click(function(e) {
        location.href="list-m?arriveDestination=" + $(this).attr("value");
    });
});