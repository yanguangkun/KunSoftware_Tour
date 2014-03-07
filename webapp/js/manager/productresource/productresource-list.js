define(function(require, exports, module) {
	 
	var $ = require('jquery'); 
	require('bootstrap')($); 
	require('jquery-form')($); 
	require('page'); 
	var lockscreen = require('lockscreen');
	$(document).ready(function(){  
		
		$(".searchBtn").click(function(e) {
            $("#searchForm").submit();
        });
		
		$(".editBtn").click(function(e) {
            var idLength = $("input[name='id']:checked").length;
			if(idLength <= 0) {alert("请选择一个进行编辑!");return}
			if(idLength >= 2) {alert("只能选择一个进行编辑!");return}
			location.href = 'edit?id=' + $("input[name='id']:checked").val();
        });
		
		$(".delBtn").click(function(e) {
			var idLength = $("input[name='id']:checked").length;
			if(idLength <= 0) {alert("请选择一个进行操作!");return}			
			if(!confirm("确定删除吗？")) {
				return;
			}
			
			$("#controlForm").attr("action","del.json");
			$("#controlForm").ajaxSubmit({
				dataType:'json', 
				success:function(data) {
					alert(data.message); 
					$("input[name='id']:checked").each(function(index, element) {
                        $(this).parent("td").parent("tr").remove();  
                    });
					
				}
			});
        });
		
		$(".createFlightCheduleBtn").click(function(e) {
            
			var idLength = $("input[name='id']:checked").length;
			if(idLength <= 0) {alert("请选择一个进行操作!");return} 
			
			lockscreen.lock();
			$("#controlForm").attr("action","createFlightChedule.json");
			$("#controlForm").ajaxSubmit({
				dataType:'json', 
				success:function(data) {
					alert(data.message); 
					lockscreen.unLock(); 
				}
			});
        });
	}); 
});