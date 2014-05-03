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
	
	$("#phoneModel").click(function(e) {
		$(this).hide();
		$(".photoalbumContent").hide();
	});
	
	$(document).on("click",".photoalbumTarget .photoalbumTimeline .s5",function() {
		$(".photoalbumBody .timeline-content-item").hide();
		var target = $(this).attr("value");
		$(".photoalbumTarget .photoalbumTimeline .s5").removeClass("cur");
		$(this).addClass("cur");
		
		$(".photoalbumBody").find(target).show();
	});
		
	$("#photoAlbum").click(function(e) {
		 
        $("#phoneModel").addClass("in").show();
		var obj = $(".photoalbumContent");//获得相应的Div对象  
		var x = ($(window).width()- 950)/2;//使用$(window).width()获得显示器的宽，并算出对应的Div离左边的距离  
		var y = ($(window).height()- 570)/2 + $(window).scrollTop() //使用$(window).height()获得显示器的高，并算出相应的Div离上边的距离  
		obj.css("top",y).css("left",x);
		
		//alert($(this).find(".timeline").html());
		$(".photoalbumBody").html($(".timeline-content").html());
		$(".photoalbumTimeline").html($(".timeline").html());
		obj.show();
		return false;
    });
});