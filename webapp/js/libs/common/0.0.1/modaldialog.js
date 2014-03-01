define(function(require, exports, module) {
		
	var $ = require('jquery'); 
	require('jquery-form')($); 
	
	var lockscreen = require('lockscreen');
	$(document).ready(function(){
		 $(document.body).append("<div id='modalDialog' class='modal fade' tabindex='-1' role='dialog' aria-labelledby='mySmallModalLabel' aria-hidden='true'><div class='modal-dialog'><div class='modal-content'> </div></div></div>");
		
		 $(".selectClean").click(function(e) {
            $(this).parent('div').parent('td').find("input[type='text']").val("");
        });
	}); 
	
	function pageClick(options) {
		 
		$(".dialogPage").click(function(e) {
			if($(this).attr("page")) {
				dialogPage($(this).attr("page"));
			} 
		});
		
		
		$("#modalDialogSearchBtn").click(function(e) {   
			modalSubmit();
			return false;
        });
		 
		function modalSubmit() { 
		 
		 	lockscreen.lock();
			$("#dialogSearchForm").ajaxSubmit({ 
				data: options.data,
				success:function(data) { 
					$(".modal-content",$('#modalDialog')).html(data);
					pageClick(options); 
					lockscreen.unLock();			
				}
			}); 
		}
		
		function dialogPage(v) {
	
			dialogPageFrm.pageNo.value = v; 
			$("#dialogPageFrm").ajaxSubmit({ 
				success:function(data) { 
					$(".modal-content",$('#modalDialog')).html(data); 
					pageClick(options); 
				}
			});
		}
	
		$("#modalDialogSelect").click(function(e) { 
		
			 
			var resultLength = $("input[name='modalDialogId']:checked").length;
			if(resultLength <= 0) {
				alert("请选择一条记录再操作!");
				return false;
			}
			 
			if(typeof options.minCount != "undefined" && resultLength < options.minCount) {
				alert("请最少选择["+options.minCount+"]条记录");
				return false;
			}
			
			if(typeof options.maxCount != "undefined" && resultLength > options.maxCount) {
				alert("请最多选择["+options.maxCount+"]条记录");
				return false;
			}
			
			var rows = [];
			var i = 0;
			
			$("input[name='modalDialogId']:checked").each(function(index, element) {						 		
                var script = "var row =" + $(this).parent().parent().find(".resultDialog").html();		
				 	
				eval(script); 
				rows[i++] = row;
            });
			
			 
			if(options.parse) {
				 options.parse(rows,options)
			} else {
				parse(rows,options)
			}
			
			$('#modalDialog').modal("hide");  
        });
		 
		$(".modalDialogTable tr .modaldialogTd").click(function(e) {	 
            var checkedV = $(this).parent("tr").find("input[type='radio'],input[type='checkbox']").attr("checked");			 
			if(typeof checkedV == "undefined") { 
				$(this).parent("tr").find("input[name='modalDialogId'][type='radio'],input[name='modalDialogId'][type='checkbox']").attr("checked",true);
				  
			} else {				 
				$(this).parent("tr").find("input[type='radio'],input[type='checkbox']").attr("checked",false);				 
				 
			}
        }); 
		 
	} 
	 
	function parse(rows,options) { 
		var row = rows[0]; 
		 
		for(var i=0; i<row.length; i++) {  
			$(options.obj).parent('div').parent('td').find("." + row[i].text).val(row[i].value);
		} 
	}
	
	function openDialog(options) {
		  
		$.ajax({ 
			url:Context_Path + "/manager/dialog/list?code="+options.code, 
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			data: options.data,
			type:'post',
			success: function(data) { 
				$(".modal-content",$('#modalDialog')).html(data);
				$('#modalDialog').modal({backdrop: 'static'});  
				pageClick(options); 
			}
		}); 
	}
	 
	module.exports = function(options) { 
		if(typeof options.code == "undefined" || options.code == "") {
			alert("选择框编号不能为空!");
		} else { 
			openDialog(options);
		}
		
	}
});