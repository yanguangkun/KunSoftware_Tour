define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-validate')($);
	require('jquery-validate-messages')($);
	require('jquery-form')($); 
	var lockscreen = require('lockscreen');
	var juicer = require('juicer'); 
	
	$(document).ready(function(){  
		var validate =  $("#saveFrm").validate({ 
			submitHandler: function(form) { 
				$(form).ajaxSubmit({
					dataType:'json', 
					success:function(data) {
						alert(data.message); 
						lockscreen.unLock();
					}
				});
			}	
		}); 
		
		$(".saveBtn").click(function(e) { 
			lockscreen.lock(); 
			var check = validate.form();  
			if(check) {
				lockscreen.lock();
			} 
			$("#saveFrm").submit();
        });
		
		$(".addBtn").click(function(e) {
			var data = {};
            var tpl = $("#tpl").html();
			var html = juicer(tpl, data); 
			var row = $("#galleryTable tr").length - 2;
			 
			var $tr = $("#galleryTable tr").eq(row);
			 
			$tr.after(html); 
			return false;
        });
		 
		$(document).on('click', '.delBtn', function () {
			delImage(this);
		}); 
		
		$(document).on('change', "input[name='galleryImageFile']", function () {
			$(this).parent("td").find("#isFile").val("1");
		});
	}); 
	
	function delImage(that) {
		var row = $("#galleryTable tr").length;
		 
		if(row <= 3) {
			alert("最后一个不能删除!");
			return;	
		}
		$(that).parent('td').parent('tr').remove();
		return false;	
	}
});