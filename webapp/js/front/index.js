define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('superslide')($); 
	require('placeholder'); 
	 
	$(document).ready(function(){ 
		 $(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true});
	}); 
	
	$(".destination").click(function(e) {
        $(".phTxt1").html($(this).text());
		 $(".destinationChild").hide();		
    });
	
	$(".destinationParent").mouseenter(function(e) {
        $(".destinationChild").show();
    });
	
	$(".destinationParent").mouseleave(function(e) {
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