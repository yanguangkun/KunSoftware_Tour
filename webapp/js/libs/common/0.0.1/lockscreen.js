define(function(require, exports, module) {
	
	var $ = require('jquery'); 
	$(document).ready(function(){ 
		 //$(document.body).append("<div class='modal modal-lock-screen hide' id='loading' tabindex='-1' role='dialog' aria-labelledby='loading' ria-hidden='true'>正在执行请稍后...<img src='/webdocs/ebidding/images/loading.gif' alt='loading...' /></div>");
		 $(document.body).append("<div id='loading' class='modal fade bs-example-modal-sm' tabindex='-1' role='dialog' aria-labelledby='mySmallModalLabel' aria-hidden='true'><div class='modal-dialog modal-sm'><div class='modal-content loading-content'> 正在执行请稍后...<img src="+Context_Path+"/images/loading.gif alt='loading...' /></div></div></div>");
		 
	}); 
	
	exports.lock = function(groups) {  
		$('#loading').modal({backdrop: 'static'});
		 
		 
	}
	
	exports.unLock = function(groups) {
		
		$('#loading').modal("hide"); 
	}
});