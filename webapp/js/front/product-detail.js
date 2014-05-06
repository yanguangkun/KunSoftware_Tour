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
			
			$.getJSON("cheduleDay.json?id=" + $("#id").val() + "&startMonth="+$(this).attr("value")+"&r=" + Math.random() , function (data, textStatus){  
				
				$(".product-day").addClass("disabled");
				for(i = 0;i < data.days.length;i++) {
					if($(".product-day[value='"+data.days[i].startDate+"']").hasClass("active")) {
						$("#cheduleDay").val("");
					}
					$(".product-day[value='"+data.days[i].startDate+"']").removeClass("disabled");
				}
				$(".product-day.active").trigger("click");
				productInfo();
			});
			 
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
			 
			totalPrice();
        });
		
		$("#ordersBtn").click(function(e) {
            $(".num").trigger("change");
			
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
					
					$("#num6").val($("#priceNum").val());
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
		if($("#isBack").val() == "1") {
			$(".product").trigger("click");
		}
		$(".product-item.active").trigger("click");
		$(".product-month.active").trigger("click"); 
		
		
	}); 
	
	
	
	var price = 0;
	var adultPrice = 0;
	var adultExtraBedPrice = 0;
	var childBedPrice = 0;
	var childNoBedPrice = 0;
	var singleRoom = 0;
	
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
				
				if(data.success == "0")  {
					//alert(data.message);
					return;
				}
				
				if($("#combo").val() == "0") {
					price = data.price;
					$("#priceNum").trigger("change");
				} else {
					adultPrice = data.adultPrice;;
					adultExtraBedPrice = data.adultExtraBedPrice;
					childBedPrice = data.childBedPrice;;
					childNoBedPrice = data.childNoBedPrice;
					singleRoom = data.singleRoom; 
					$(".num").trigger("change");
				}
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
	
	function totalPrice() {
		
		var totalPrice = 0;
		var sNum1V = parseInt($("#sNum1").val());
		var sNum2V = parseInt($("#sNum2").val());
		var sNum3V = parseInt($("#sNum3").val());
		var sNum4V = parseInt($("#sNum4").val());
		
		if(sNum1V != 0) {
			totalPrice += sNum1V * adultPrice
		}
		
		if(sNum2V != 0) {
			totalPrice += sNum2V * adultExtraBedPrice
		}
		
		if(sNum3V != 0) {
			totalPrice += sNum3V * childBedPrice
		}
		
		if(sNum4V != 0) {
			totalPrice += sNum4V * childNoBedPrice
		}
		 
		if(totalPrice == "0") {
			$(".allPrice").html("￥0");
			$(".avgPrice").html("￥0");
			return;
		}
		var avgPrice = totalPrice /(sNum1V + sNum2V + sNum3V + sNum4V); 
		$(".allPrice").html("￥" + Math.round(totalPrice));
		$(".avgPrice").html("￥" + Math.round(avgPrice)); 
	}
	
	$(".indexDestination").mouseenter(function(e) {
        $(".arr").show();
		$(".indexDestinationList").show();
    });
	
	$(".indexDestination").mouseleave(function(e) {
        $(".arr").hide();
		$(".indexDestinationList").hide();
    });
});