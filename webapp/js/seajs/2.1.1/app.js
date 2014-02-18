var Context_Path = "/tour";

(function() { 
	var alias = {
		"jquery": "libs/jquery/1.10.2/jquery.js",
		"jquery-form": "libs/jquery-form/3.49/jquery-form.js",
		"page": "libs/common/0.0.1/page.js"
	}; 
	seajs.config({
		base: Context_Path + "/js/",
		alias: alias 
	}); 
})();
 