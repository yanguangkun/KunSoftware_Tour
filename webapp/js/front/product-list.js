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
					if(data.totalPages == "1") return;
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
			
			var banner = $(this).val();
			if(banner == "3") {
				$(".groundInfo").show();
				$(".questionsData").hide();
				$.getJSON("ground.json?destination=" + $("#arriveDestination").val() + "&r=" + Math.random(), function (data, textStatus){ 				
					$(".groundInfo").html(data.result); 
				});
			} else {
				if(banner == -1) banner = 0;
				banner = banner + 1; 
				$("#banner").val(banner);
				$(".groundInfo").hide();
				$(".questionsData").show();
				$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + banner + "&r=" + Math.random() , function (data, textStatus){  
					$(".questionsInfo").html(data.result); 
				});
			}
        });
		
		$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + $("#banner").val()+ "&r=" + Math.random() , function (data, textStatus){  
			$(".questionsInfo").html(data.result); 
		});
		
		$(document).on("click",".questionsInfo .page",function(){ 
			$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + $("#banner").val() + "&pageNo=" + $(this).attr("value" ) + "&r=" + Math.random(), function (data, textStatus){  
				
				$(".questionsInfo").html(data.result);
			});
		});
		
		$(document).on("click",".groundInfo .page",function(){ 
			$.getJSON("ground.json?destination=" + $("#arriveDestination").val() + "&pageNo=" + $(this).attr("value" ) + "&r=" + Math.random(), function (data, textStatus){  
				
				$(".groundInfo").html(data.result); 
			});
		});
		
		$("#questionsBtn").click(function(e) {
            
			$.post("questionsSave.json",{
				'destination':$("#destination").val(),
				'banner':$("#banner").val(),
				'content':$("#content").val()	
			},function(data) {
				alert(data.message); 
				$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + $("#banner").val()+ "&r=" + Math.random() , function (data, textStatus){  
					$(".questionsInfo").html(data.result); 
				});
			},"json");
			
			return false;
        });
		
		$(".somePraise").click(function(e) {
			var that = this;
			$.getJSON("praise.json?id=" + $(this).attr("value"), function (data, textStatus){   
				$(that).parent().find(".somePraiseV").html(data.somePraise);
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