define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder');  
	$(document).ready(function(){ 
		$(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true,interTime:5000}); 
		
		$(".arr").click(function(e) {
            var obj = $(this).parent().parent().find(".introduce"); 
			if(obj.is(":visible")) { 
				obj.hide();
				$(this).addClass("expand");
			} else {
				obj.show();
				$(this).removeClass("expand");
			}
			
        });
		
		$(".productHide").click(function(e) {
           
			$(".productPrice").slideUp(function() {
				 $(".productP").slideDown();
			}); 
		});
		$(".product").click(function(e) {
            $(".productP").hide();
			$(".productPrice").slideDown();
        });
		
		$(".product-item").click(function(e) {
			if($(this).hasClass("disabled")) return; 
            $(".product-item").removeClass("active");
            $(this).addClass("active");
			$("#tplId").val($(this).attr("value"));
			productInfo();
			return false;
        });
		
		$(".product-month").click(function(e) {
			if($(this).hasClass("disabled")) return;
			
			$(".product-month").removeClass("active");
            $(this).addClass("active");
			$("#cheduleMonth").val($(this).attr("value"));
			productInfo();
			return false;
        });
		
		$(".product-day").click(function(e) {
			if($(this).hasClass("disabled")) return;
			
			$(".product-day").removeClass("active");
            $(this).addClass("active");
			$("#cheduleDay").val($(this).attr("value"));
			productInfo();
			return false;
        });
		
		
		$.getJSON("comments.json?productResourceId=" + $("#id").val() + "&r=" + Math.random() , function (data, textStatus){  
			$(".commentsInfo").html(data.result);
			 
		});
		
		$(document).on("click",".page",function(){ 
			$.getJSON("comments.json?productResourceId=" + $("#id").val() + "&pageNo=" + $(this).attr("value" ) + "&r=" + Math.random(), function (data, textStatus){  
				
				$(".commentsInfo").html(data.result);
			});
		});
		
		$("#commentsBtn").click(function(e) {
            
			$.post("commentsSave.json",{
				'productResourceId':$("#productResourceId").val(),
				'productResourceName':$("#productResourceName").val(),
				'content':$("#content").val()	
			},function(data) {
				alert(data.message);
			},"json");
			
			return false;
        });
		
		$(".num").change(function(e) {
            var target = $(this).attr("target");
			$(target).val($(this).val());
        });
		
		$("#ordersBtn").click(function(e) {
            
			$.getJSON("checkLogin.json" , function (data, textStatus){  
				if(data.isLogin == "0") {
					alert(data.message);
					return false;
				} 
				
				if($("#tplId").val() == "") {
					alert("请选择类别!");
					return false;
				}
				
				if($("#cheduleMonth").val() == "") {
					alert("请选择预订月份!");
					return false;
				}
				
				if($("#cheduleDay").val() == "") {
					alert("请选择预订日期!");
					return false;
				}
				
				if($("#combo").val() == "0") {
					if($("#priceNum").val() == "") {
						alert("请输入预订数量!");
						return false;
					}
				} else {
					if($("#num1").val() == "0" && $("#num2").val() == "0" && 
					$("#num3").val() == "0" && $("#num4").val() == "0") {
						alert("请选择出行人数!");
						return false;
					}
				}
				
				$("#priceFrm").attr("action","buy"); 
				$("#priceFrm").submit();
			}); 
			
			return false;
        });
	}); 
	
	
	
	var price = 0;
	function productInfo() {
		
		var tplV = $("#tplId").val();
		var cheduleMonthV = $("#cheduleMonth").val();
		var cheduleDayV = $("#cheduleDay").val();
		 
		if(tplV == "" || cheduleMonthV == "" || cheduleDayV == "") {
			return "";
		} 
		$("#priceFrm").ajaxSubmit({
			dataType:'json', 
			success:function(data) { 
				price = data.price;
				
				$("#priceNum").trigger("change");
			}
		});
	}
	
	$("#priceNum").change(function(e) {
		
		if($(this).val() == "") {
			$(".allPrice").html("￥0");
			$(".avgPrice").html("￥0");
			return;
		}
        var v = $(this).val() * price;
		$(".allPrice").html(v);
		$(".avgPrice").html(price);
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