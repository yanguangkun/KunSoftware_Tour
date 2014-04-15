define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('superslide')($); 
	require('placeholder'); 
	require('frontpage');
	var lockscreen = require('lockscreen');
	$(document).ready(function(){  
		$(".destination").click(function(e) {
            location.href = "list?destination=" + $(this).attr("value");
        }); 
		
		//
		 
		$(".photo_item").click(function(e) {
            
			$("#phoneModel").addClass("in").show();
			var obj = $(".photoalbumContent");//获得相应的Div对象  
			var x = ($(window).width()- 950)/2;//使用$(window).width()获得显示器的宽，并算出对应的Div离左边的距离  
			var y = ($(window).height()- 570)/2;//使用$(window).height()获得显示器的高，并算出相应的Div离上边的距离  
			obj.css("top",y).css("left",x);  
			 
			obj.show();
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
	 
});