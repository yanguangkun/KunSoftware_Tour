var Context_Path = "/tour";

(function() { 
	var alias = {
		"jquery": "libs/jquery/1.10.2/jquery.js",
		"jquery-form": "libs/jquery-form/3.49/jquery-form.js",
		"bootstrap": "libs/bootstrap/3.1.1/bootstrap.min.js",
		"superslide": "libs/superslide/2.2.1/jquery.SuperSlide.2.1.1.js",
		'jquery-validate': 'libs/jquery-validate/1.11.1/jquery.validate.min.js',
		'jquery-validate-messages': 'libs/jquery-validate/1.11.1/messages_cn.js',
		"lockscreen": "libs/common/0.0.1/lockscreen.js",
		'placeholder':'libs/common/0.0.1/placeholder.js',
		'wdatepicker': 'libs/wdatepicker/4.8/wdatepicker.js',
		"cascade": "libs/common/0.0.1/cascade.js",
		"frontpage": "libs/common/0.0.1/frontpage.js"
	}; 
	seajs.config({
		base: Context_Path + "/js/",
		alias: alias 
	}); 
})();
 