define([], function() { return function(jQuery) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值"),
		fixlength:"请输入长度为9的字符",
		phone:"请输入合法的手机号码",
		card:"请输入合法的身份证号",
		telephone:"电话号码带区号加“-”",
		fax:"传真号码带区号加“-”",
		post:"请输入合法的邮政编号",
		character:"请不要输入特殊字符",
		english:"请输入合法的英文字符",
		chinese:"请输入合法的汉字",
		money:"请输入合法的金额",
		amount:"请输入合法的金额",
		exchangeRate:"请输入正确的汇率",
		mobilephone:"输入手机或电话号码带区号加“-”",
		cardId:"请输入正确的证号",
		usernames:"请输入正确的用户名",
		scoreformat:"请输入小数点后保留两位的数值",
		scoreformat1:"请输入小于100的数值",
		plus:"请输入正确的正整数",
		chinaEn:"请输入正确的中英文",
		isOrgCode:"请输入长度为4位的字符",
		notChinese:"不能输入中文",
		percent:"请输入合法的数字",
		percent_one:"请输入合法的数字(保留一位小数)"
});

}});
