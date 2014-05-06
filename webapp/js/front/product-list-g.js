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
			$("#tag").val(""); 
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
		
		$(".tag").click(function(e) {
			page = 0; 
            $("#pageNo").val(page++); 
			$("#tag").val($(this).text()); 
			$(".productItem").remove();
			$("#listMore").ajaxSubmit({
				dataType:'json', 
				success:function(data) { 
					$("#productList").append(data.result);
					totalPages = data.totalPages;
				}
			});
			return false;
        }); 
		
		$(document).on("click",".somePraise",function(e) {
			var that = this;
			$.getJSON("praise.json?id=" + $(this).attr("value"), function (data, textStatus){   
				$(that).parent().find(".somePraiseV").html(data.somePraise);
			}); 
        });
		
		$("#questionsBtn").click(function(e) {
            
			$.post("questionsSave.json",{
				'destination':$("#destination").val(),
				'banner':$("#banner").val(),
				'content':$("#content").val()	
			},function(data) { 
				$.getJSON("questions.json?destination=" + $("#destination").val() + "&banner=" + $("#banner").val()+ "&r=" + Math.random() , function (data, textStatus){  
					$(".questionsInfo").html(data.result); 
					$("#content").val("");
				});
			},"json");
			
			return false;
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