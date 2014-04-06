define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder');  
	$(document).ready(function(){ 
		$(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true}); 
		
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
});