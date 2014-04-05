define(function(require, exports, module) {
	
	var $ = require('jquery'); 
	$(document).ready(function(){  
		editElem = $('form');
		editElem.find('input').each(
			function (idx, elem) {
				var e = $(elem),tips = e.nextAll('.phTxt'); 
				tips[$.trim(e.val()) ? 'hide' : 'show']();
			}).focus(
			function () {
				var e = $(this); 
				e.nextAll('.phTxt').hide();
			}).blur(function () {
				var e = $(this);
				e.nextAll('.phTxt')[$.trim(e.val()) ? 'hide' : 'show']();
			});
		editElem.find('.phTxt').click(function () {
			$(this).hide().prevAll('input').focus();
		});
	});
    
});