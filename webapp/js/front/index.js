define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('superslide')($); 
	require('placeholder'); 
	 
	$(document).ready(function(){ 
		 $(".slideBox").slide({mainCell:".bd ul",effect:"leftLoop",autoPlay:true,interTime:5000});
	}); 
	
	$(".destination").click(function(e) {
        $(".phTxt1").html($(this).text());
		$(".destinationChild").hide();	
		$("#destination").val($(this).text());
		
    });
	
	$(".customizeBtn").click(function(e) {
		 
        location.href = "customize/info1?chufa="+$("#chufa").val() +"&destination=" + $("#destination").val();	
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