var Context_Path = "/tour";

(function() { 
	var alias = {
		"jquery": "libs/jquery/1.11.0/jquery-1.11.0.min.js",
		"jquery-form": "libs/jquery-form/3.49/jquery-form.js",
		"bootstrap": "libs/bootstrap/3.1.1/bootstrap.min.js",
		'jquery-validate': 'libs/jquery-validate/1.12.0/jquery.validate.js',
		'jquery-validate-messages': 'libs/jquery-validate/1.12.0/messages_cn.js',
		'jquery-wysiwyg': 'libs/jquery-wysiwyg/0.98/jquery.wysiwyg.js',		
		'wdatepicker': 'libs/wdatepicker/4.8/wdatepicker.js',
		'datepicker':'libs/common/0.0.1/datepicker.js', 
		'juicer':'libs/juicer/0.6.5/juicer-min.js', 
		"lockscreen": "libs/common/0.0.1/lockscreen.js",
		"modaldialog": "libs/common/0.0.1/modaldialog.js",
		"cascade": "libs/common/0.0.1/cascade.js",
		"page": "libs/common/0.0.1/page.js"
	}; 
	seajs.config({
		base: Context_Path + "/js/",
		debug:2,
		alias: alias	 
	}); 
})();
 